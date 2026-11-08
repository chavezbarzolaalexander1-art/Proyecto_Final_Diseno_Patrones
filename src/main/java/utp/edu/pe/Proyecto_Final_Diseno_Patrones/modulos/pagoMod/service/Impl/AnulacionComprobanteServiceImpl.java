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
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.StockInventarioService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnulacionComprobanteServiceImpl implements AnulacionComprobanteService {
    private final ComprobantePagoRepository comprobanteRepository;
    private final AnulacionComprobanteRepository anulacionRepository;
    private final StockInventarioService stockInventarioService;
    private final ContextoEstadoComprobante contextoEstado;

    @Override
    @Transactional
    public void anularComprobante(Long comprobanteId, AnulacionRequest request) {
        ComprobantePago comprobante = comprobanteRepository.findById(comprobanteId)
                .orElseThrow(() -> new RuntimeException("No se encontró el comprobante con ID: " + comprobanteId));

        // 1. Validar anulación mediante el patrón State
        EstadoComprobanteState estadoActual = contextoEstado.obtenerEstado(comprobante.getEstadoComprobante());
        estadoActual.validarPermiteAnulacion(comprobante);

        // 2. Reversión de inventario delegada al módulo de productos
        for (DetalleComprobante detalle : comprobante.getDetalles()) {
            stockInventarioService.restaurarStock(detalle.getProducto().getId(), detalle.getCantidad());
        }

        // 3. Registrar auditoría de la anulación
        AnulacionComprobante anulacion = AnulacionComprobante.builder()
                .motivo(request.motivo().trim())
                .fechaAnulacion(LocalDateTime.now())
                .comprobantePago(comprobante)
                .build();
        anulacionRepository.save(anulacion);

        // 4. Cambiar estado fiscal a ANULADO
        comprobante.setEstadoComprobante(EstadoComprobante.ANULADO);
        comprobanteRepository.save(comprobante);
    }
}
