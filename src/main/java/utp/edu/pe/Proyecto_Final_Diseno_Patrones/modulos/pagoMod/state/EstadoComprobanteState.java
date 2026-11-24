package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.state;

import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.ComprobantePago;

/**
 * Interfaz del patrón State que define el comportamiento permitido y las transiciones de estado
 * para un {@link ComprobantePago}.
 */
public interface EstadoComprobanteState {

  /**
   * Valida si el comprobante se encuentra en un estado válido para recibir un nuevo pago.
   *
   * @param comprobante comprobante de pago a evaluar
   */
  void validarPermitePago(ComprobantePago comprobante);

  /**
   * Evalúa y actualiza el estado del comprobante luego de registrar un pago.
   *
   * @param comprobante comprobante de pago a actualizar
   * @param totalPagadoAcumulado monto total acumulado pagado hasta el momento
   */
  void evaluarSiguienteEstadoTrasPago(ComprobantePago comprobante, double totalPagadoAcumulado);

  /**
   * Valida si el comprobante se encuentra en un estado válido para ser anulado.
   *
   * @param comprobante comprobante de pago a evaluar
   */
  void validarPermiteAnulacion(ComprobantePago comprobante);
}