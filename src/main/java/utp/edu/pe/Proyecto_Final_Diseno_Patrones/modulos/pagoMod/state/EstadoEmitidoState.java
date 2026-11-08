package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.state;

import org.springframework.stereotype.Component;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.ComprobantePago;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.EstadoComprobante;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model.EstadoPago;

import java.math.BigDecimal;
@Component
public class EstadoEmitidoState implements  EstadoComprobanteState{
    @Override
    public void validarPermitePago(ComprobantePago comprobante) {
        if (comprobante.getEstadoPago() == EstadoPago.PAGADO) {
            throw new IllegalArgumentException("El comprobante ya se encuentra totalmente PAGADO.");
        }
    }

    @Override
    public void evaluarSiguienteEstadoTrasPago(ComprobantePago comprobante, double totalPagadoAcumulado) {
        BigDecimal pagado = BigDecimal.valueOf(totalPagadoAcumulado);
        if (pagado.compareTo(comprobante.getTotal()) >= 0) {
            comprobante.setEstadoPago(EstadoPago.PAGADO);
        } else {
            comprobante.setEstadoPago(EstadoPago.PENDIENTE);
        }
    }

    @Override
    public void validarPermiteAnulacion(ComprobantePago comprobante) {
        // Permitido anular comprobantes emitidos (pendientes o pagados)
    }
}
