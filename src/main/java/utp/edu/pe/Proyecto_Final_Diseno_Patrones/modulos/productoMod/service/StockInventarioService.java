package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service;

import java.util.List;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.StockInventarioResponse;

/**
 * Interfaz de servicio que define las operaciones de negocio para la gestión y control
 * del inventario de productos en el sistema.
 */
public interface StockInventarioService {

  /**
   * Busca y recupera el estado actual del inventario asociado a un producto específico.
   *
   * @param productoId identificador único del producto
   * @return DTO de respuesta con los datos del stock del producto
   */
  StockInventarioResponse buscarPorProductoId(Long productoId);

  /**
   * Actualiza de forma directa la cantidad disponible en inventario de un producto.
   *
   * @param productoId identificador único del producto
   * @param nuevaCantidad nueva cantidad total de unidades disponibles
   * @return DTO de respuesta con el estado actualizado del inventario
   */
  StockInventarioResponse actualizarCantidadStock(Long productoId, Integer nuevaCantidad);

  /**
   * Obtiene el listado de todos los productos cuyo stock disponible es menor o igual
   * al umbral mínimo configurado.
   *
   * @return lista de DTOs de respuesta con los registros de inventario en estado de alerta
   */
  List<StockInventarioResponse> listarProductosConStockBajo();

  /**
   * Disminuye la cantidad disponible en inventario de un producto (por ejemplo, tras una venta).
   *
   * @param productoId identificador único del producto
   * @param cantidad cantidad de unidades a descontar del stock
   */
  void descontarStock(Long productoId, Integer cantidad);

  /**
   * Incrementa la cantidad disponible en inventario de un producto (por ejemplo, tras una anulación
   * o devolución).
   *
   * @param productoId identificador único del producto
   * @param cantidad cantidad de unidades a reponer en el stock
   */
  void restaurarStock(Long productoId, Integer cantidad);
}