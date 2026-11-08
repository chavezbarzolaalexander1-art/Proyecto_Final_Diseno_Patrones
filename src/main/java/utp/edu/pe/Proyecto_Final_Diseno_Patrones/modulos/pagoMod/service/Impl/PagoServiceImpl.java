package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.ComprobantePago;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.repository.ComprobantePagoRepository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.dto.PagoResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.dto.RegistrarPagoRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model.MetodoPago;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model.Pago;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.repository.MetodoPagoRepository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.repository.PagoRepository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.service.PagoService;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.state.ContextoEstadoComprobante;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.state.EstadoComprobanteState;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.strategy.ProcesadorPago;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.strategy.ResultadoPago;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Registra y consulta pagos. La validación y la comisión de cada medio de pago se
 * delegan en el patrón Strategy (ProcesadorPago), por lo que este servicio no contiene
 * condicionales por tipo de pago.
 */
@Service
@RequiredArgsConstructor
public class PagoServiceImpl implements PagoService {
    private final PagoRepository pagoRepository;
    private final MetodoPagoRepository metodoPagoRepository;
    private final ComprobantePagoRepository comprobanteRepository;
    private final ProcesadorPago procesadorPago;
    private final ContextoEstadoComprobante contextoEstado;
    @Override
    @Transactional
    public PagoResponse registrarPago(RegistrarPagoRequest request) {
        ComprobantePago comprobante = comprobanteRepository.findById(request.comprobanteId())
                .orElseThrow(() -> new RuntimeException("No se encontró el comprobante con ID: " + request.comprobanteId()));

        // 1. Validar según el estado fiscal actual (EMITIDO o ANULADO) y financiero (PAGADO)
        EstadoComprobanteState estadoActual = contextoEstado.obtenerEstado(comprobante.getEstadoComprobante());
        estadoActual.validarPermitePago(comprobante);

        // 2. Crear la entidad Pago
        Pago pago = Pago.builder()
                .monto(request.monto())
                .fechaPago(LocalDateTime.now())
                .numeroTransaccion(request.numeroTransaccion())
                .comprobantePago(comprobante)
                .metodoPago(obtenerOCrearMetodo(request.metodoPago()))
                .build();

        // 3. Procesar el pago con el patrón Strategy
        ResultadoPago resultado = procesadorPago.procesar(pago);
        pago.setComision(resultado.comision());
        pago.setMontoNeto(resultado.montoNeto());

        // 4. Guardar pago en BD
        Pago guardado = pagoRepository.save(pago);

        // 5. Evaluar si cambia estadoPago de PENDIENTE a PAGADO
        double totalPagado = calcularTotalPagado(comprobante.getId());
        estadoActual.evaluarSiguienteEstadoTrasPago(comprobante, totalPagado);
        comprobanteRepository.save(comprobante);

        return new PagoResponse(
                guardado.getId(),
                comprobante.getId(),
                resultado.metodo(),
                resultado.monto(),
                resultado.comision(),
                resultado.montoNeto(),
                guardado.getNumeroTransaccion(),
                guardado.getFechaPago()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<PagoResponse> listarPorComprobante(Long comprobanteId) {
        return pagoRepository.findByComprobantePagoId(comprobanteId).stream()
                .map(p -> new PagoResponse(
                        p.getId(),
                        comprobanteId,
                        p.getMetodoPago().getNombre(),
                        p.getMonto(),
                        p.getComision(),
                        p.getMontoNeto(),
                        p.getNumeroTransaccion(),
                        p.getFechaPago()
                ))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Double calcularTotalPagado(Long comprobanteId) {
        BigDecimal total = pagoRepository.findByComprobantePagoId(comprobanteId).stream()
                .map(p -> BigDecimal.valueOf(p.getMonto()))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);

        return total.doubleValue();
    }

    private MetodoPago obtenerOCrearMetodo(String nombre) {
        String metodo = nombre.trim().toUpperCase();
        if (!procesadorPago.soporta(metodo)) {
            throw new IllegalArgumentException("Método de pago no soportado: " + nombre);
        }
        return metodoPagoRepository.findByNombre(metodo)
                .orElseGet(() -> metodoPagoRepository.save(MetodoPago.builder().nombre(metodo).build()));
    }
}
