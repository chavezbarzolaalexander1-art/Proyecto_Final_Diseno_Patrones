package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service;

import java.util.List;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.UnidadMedidaRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.UnidadMedidaResponse;

/**
 * Interfaz de servicio que define las operaciones de negocio para la gestión de unidades
 * de medida estándar aplicables a los productos del sistema.
 */
public interface UnidadMedidaService {

  /**
   * Registra una nueva unidad de medida en el sistema.
   *
   * @param request DTO con los datos de la unidad de medida a registrar
   * @return DTO de respuesta con los datos de la unidad de medida creada
   */
  UnidadMedidaResponse registrarUnidadMedida(UnidadMedidaRequest request);

  /**
   * Obtiene el listado completo de todas las unidades de medida registradas.
   *
   * @return lista de DTOs de respuesta con las unidades de medida disponibles
   */
  List<UnidadMedidaResponse> listarTodas();

  /**
   * Busca y recupera una unidad de medida específica mediante su identificador único.
   *
   * @param id identificador único de la unidad de medida
   * @return DTO de respuesta con los datos de la unidad de medida encontrada
   */
  UnidadMedidaResponse buscarPorId(Long id);

  /**
   * Actualiza los datos de una unidad de medida existente.
   *
   * @param id identificador único de la unidad de medida a modificar
   * @param request DTO con los nuevos datos de la unidad de medida
   * @return DTO de respuesta con la información actualizada
   */
  UnidadMedidaResponse actualizarUnidadMedida(Long id, UnidadMedidaRequest request);

  /**
   * Elimina una unidad de medida del sistema según su identificador único.
   *
   * @param id identificador único de la unidad de medida a eliminar
   */
  void eliminarUnidadMedida(Long id);
}