package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.ComprobantePago;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.DetalleComprobante;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.EstadoComprobante;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.repository.ComprobantePagoRepository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.dto.AnulacionRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model.AnulacionComprobante;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.repository.AnulacionComprobanteRepository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.service.AnulacionComprobanteService;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.state.ContextoEstadoComprobante;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.state.EstadoComprobanteState;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.StockInventario;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.repository.StockInventarioRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnulacionComprobanteServiceImpl implements AnulacionComprobanteService {
    private final ComprobantePagoRepository comprobanteRepository;
    private final AnulacionComprobanteRepository anulacionRepository;
    private final StockInventarioRepository stockInventarioRepository;
    private final ContextoEstadoComprobante contextoEstado;

    @Override
    @Transactional
    public void anularComprobante(Long comprobanteId, AnulacionRequest request) {
        ComprobantePago comprobante = comprobanteRepository.findById(comprobanteId)
                .orElseThrow(() -> new RuntimeException("No se encontró el comprobante con ID: " + comprobanteId));

        // 1. Validar si el comprobante permite ser anulado según su estado actual (Patrón State)
        EstadoComprobanteState estadoActual = contextoEstado.obtenerEstado(comprobante.getEstado());
        estadoActual.validarPermiteAnulacion(comprobante);

        // 2. Reversión de stock en inventario por cada ítem del comprobante
        for (DetalleComprobante detalle : comprobante.getDetalles()) {
            StockInventario stock = stockInventarioRepository.findByProductoId(detalle.getProducto().getId())
                    .orElseThrow(() -> new RuntimeException("No se encontró stock registrado para el producto ID: " + detalle.getProducto().getId()));

            stock.setCantidadDisponible(stock.getCantidadDisponible() + detalle.getCantidad());
            stock.setUltimaActualizacion(LocalDateTime.now());
            stockInventarioRepository.save(stock);
        }

        // 3. Registrar auditoría y motivo de anulación
        AnulacionComprobante anulacion = AnulacionComprobante.builder()
                .motivo(request.motivo().trim())
                .fechaAnulacion(LocalDateTime.now())
                .comprobantePago(comprobante)
                .build();
        anulacionRepository.save(anulacion);

        // 4. Actualizar estado del comprobante a ANULADO
        comprobante.setEstado(EstadoComprobante.ANULADO);
        comprobanteRepository.save(comprobante);
    }
}
