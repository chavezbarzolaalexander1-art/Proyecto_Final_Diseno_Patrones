package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.service;

import java.util.List;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.dto.PagoResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.dto.RegistrarPagoRequest;

/**
 * Servicio que define las operaciones de negocio para la gestión de pagos.
 */
public interface PagoService {

  /**
   * Registra un nuevo pago asociado a un comprobante.
   *
   * @param request datos de solicitud del pago a registrar
   * @return información detallada del pago registrado
   */
  PagoResponse registrarPago(RegistrarPagoRequest request);

  /**
   * Obtiene la lista de pagos registrados para un comprobante específico.
   *
   * @param comprobanteId identificador único del comprobante de pago
   * @return lista de pagos asociados al comprobante
   */
  List<PagoResponse> listarPorComprobante(Long comprobanteId);

  /**
   * Calcula el monto total pagado hasta el momento para un comprobante específico.
   *
   * @param comprobanteId identificador único del comprobante de pago
   * @return suma total de los pagos realizados al comprobante
   */
  Double calcularTotalPagado(Long comprobanteId);
}
