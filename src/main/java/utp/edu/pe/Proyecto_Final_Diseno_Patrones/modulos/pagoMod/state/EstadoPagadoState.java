package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.state;

import org.springframework.stereotype.Component;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.ComprobantePago;
@Component
public class EstadoPagadoState implements EstadoComprobanteState{
    @Override
    public void validarPermitePago(ComprobantePago comprobante) {
        throw new IllegalArgumentException("El comprobante ya se encuentra totalmente PAGADO.");
    }

    @Override
    public void evaluarSiguienteEstadoTrasPago(ComprobantePago comprobante, double totalPagadoAcumulado) {
        // Permanece en PAGADO.
    }

    @Override
    public void validarPermiteAnulacion(ComprobantePago comprobante) {
        throw new IllegalArgumentException("No se puede anular un comprobante que ya fue PAGADO.");
    }
}
