package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.state;

import org.springframework.stereotype.Component;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.ComprobantePago;

@Component
public class EstadoAnuladoState implements EstadoComprobanteState {
    @Override
    public void validarPermitePago(ComprobantePago comprobante) {
        throw new IllegalArgumentException("No se pueden registrar pagos sobre un comprobante ANULADO.");
    }

    @Override
    public void evaluarSiguienteEstadoTrasPago(ComprobantePago comprobante, double totalPagadoAcumulado) {
        // Sin cambios.
    }

    @Override
    public void validarPermiteAnulacion(ComprobantePago comprobante) {
        throw new IllegalArgumentException("El comprobante ya se encuentra ANULADO.");
    }
}
