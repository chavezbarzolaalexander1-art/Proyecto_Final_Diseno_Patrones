package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service;

import java.util.List;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.ProductoRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.ProductoResponse;

/**
 * Interfaz de servicio que define las operaciones de negocio para la gestión de productos
 * en el catálogo del sistema.
 */
public interface ProductoService {

  /**
   * Registra un nuevo producto en el catálogo junto con su inventario inicial.
   *
   * @param request DTO con los datos del producto a registrar
   * @return DTO de respuesta con los datos del producto creado
   */
  ProductoResponse registrarProducto(ProductoRequest request);

  /**
   * Obtiene el listado completo de todos los productos registrados.
   *
   * @return lista de DTOs de respuesta con los productos disponibles
   */
  List<ProductoResponse> listarTodos();

  /**
   * Busca y recupera un producto específico mediante su identificador único.
   *
   * @param id identificador único del producto
   * @return DTO de respuesta con los datos del producto encontrado
   */
  ProductoResponse buscarPorId(Long id);

  /**
   * Busca y recupera un producto específico mediante su código único de catálogo.
   *
   * @param codigo código único identificador del producto
   * @return DTO de respuesta con los datos del producto encontrado
   */
  ProductoResponse buscarPorCodigo(String codigo);

  /**
   * Actualiza los datos de un producto existente.
   *
   * @param id identificador único del producto a modificar
   * @param request DTO con los nuevos datos del producto
   * @return DTO de respuesta con la información actualizada
   */
  ProductoResponse actualizarProducto(Long id, ProductoRequest request);

  /**
   * Elimina un producto del catálogo según su identificador único.
   *
   * @param id identificador único del producto a eliminar
   */
  void eliminarProducto(Long id);
}