package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service;

import java.util.List;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.CategoriaRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.CategoriaResponse;

/**
 * Interfaz de servicio que define las operaciones de negocio para la gestión de categorías
 * de productos.
 */
public interface CategoriaService {

  /**
   * Registra una nueva categoría en el sistema.
   *
   * @param request DTO con los datos de la categoría a registrar
   * @return DTO de respuesta con los datos de la categoría creada
   */
  CategoriaResponse registrarCategoria(CategoriaRequest request);

  /**
   * Obtiene el listado completo de todas las categorías registradas.
   *
   * @return lista de DTOs de respuesta con las categorías disponibles
   */
  List<CategoriaResponse> listarTodas();

  /**
   * Busca y recupera una categoría específica mediante su identificador único.
   *
   * @param id identificador único de la categoría
   * @return DTO de respuesta con los datos de la categoría encontrada
   */
  CategoriaResponse buscarPorId(Long id);

  /**
   * Actualiza los datos de una categoría existente.
   *
   * @param id identificador único de la categoría a modificar
   * @param request DTO con los nuevos datos de la categoría
   * @return DTO de respuesta con la información actualizada
   */
  CategoriaResponse actualizarCategoria(Long id, CategoriaRequest request);

  /**
   * Elimina una categoría del sistema según su identificador único.
   *
   * @param id identificador único de la categoría a eliminar
   */
  void eliminarCategoria(Long id);
}