package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.service;

import java.util.List;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.dto.ComprobanteResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.dto.CrearComprobanteRequest;

/**
 * Servicio que define las operaciones de negocio para la gestión de comprobantes de pago.
 */
public interface ComprobanteService {
  /**
   * Emite y registra un nuevo comprobante de pago a partir de la solicitud recibida.
   *
   * @param request datos necesarios para emitir el comprobante
   * @return información del comprobante emitido
   */
  ComprobanteResponse emitir(CrearComprobanteRequest request);

  /**
   * Obtiene la lista completa de comprobantes de pago registrados.
   *
   * @return lista de comprobantes registrados
   */
  List<ComprobanteResponse> listarTodos();

  /**
   * Busca un comprobante de pago por su identificador único.
   *
   * @param id identificador único del comprobante
   * @return información del comprobante encontrado
   */
  ComprobanteResponse buscarPorId(Long id);
}
