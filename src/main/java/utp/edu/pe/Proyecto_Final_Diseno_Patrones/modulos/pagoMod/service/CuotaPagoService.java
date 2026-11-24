package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.service;

import java.util.List;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.dto.CuotaPagoResponse;

/**
 * Servicio que define las operaciones de negocio para la gestión de cuotas de pago.
 */
public interface CuotaPagoService {

  /**
   * Genera el cronograma de cuotas de pago para un comprobante específico.
   *
   * @param comprobanteId identificador único del comprobante de pago
   * @param numeroCuotas cantidad total de cuotas a generar
   * @param diasEntreCuotas intervalo de días entre cada cuota
   */
  void generarCronogramaCuotas(Long comprobanteId, Integer numeroCuotas, Integer diasEntreCuotas);

  /**
   * Obtiene la lista de cuotas de pago asociadas a un comprobante específico.
   *
   * @param comprobanteId identificador único del comprobante de pago
   * @return lista de cuotas registradas para el comprobante
   */
  List<CuotaPagoResponse> listarPorComprobante(Long comprobanteId);

  /**
   * Marca una cuota de pago como pagada registrando el método y el número de transacción.
   *
   * @param cuotaId identificador único de la cuota de pago
   * @param metodoPago nombre del método utilizado para el pago
   * @param numeroTransaccion número de operación o transacción asociado al pago
   */
  void marcarCuotaComoPagada(Long cuotaId, String metodoPago, String numeroTransaccion);
}
