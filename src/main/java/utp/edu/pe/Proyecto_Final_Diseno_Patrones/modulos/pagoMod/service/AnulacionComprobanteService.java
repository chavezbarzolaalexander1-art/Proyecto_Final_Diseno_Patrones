package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.service;

import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.dto.AnulacionRequest;

/**
 * Servicio que define las operaciones de negocio para la anulación de comprobantes de pago.
 */
public interface AnulacionComprobanteService {
  /**
   * Ejecuta el proceso de anulación de un comprobante de pago existente.
   *
   * @param comprobanteId identificador único del comprobante a anular
   * @param request       datos de la solicitud con el motivo de anulación
   */
  void anularComprobante(Long comprobanteId, AnulacionRequest request);
}
