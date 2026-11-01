package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.state;

import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.ComprobantePago;

public interface EstadoComprobanteState {
    void validarPermitePago(ComprobantePago comprobante);
    void evaluarSiguienteEstadoTrasPago(ComprobantePago comprobante, double totalPagadoAcumulado);
    void validarPermiteAnulacion(ComprobantePago comprobante);
}
