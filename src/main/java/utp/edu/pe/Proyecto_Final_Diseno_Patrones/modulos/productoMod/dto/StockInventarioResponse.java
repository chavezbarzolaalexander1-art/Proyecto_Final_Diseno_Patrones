package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto;

import java.time.LocalDateTime;

/**
 * DTO de respuesta con el estado actual del stock de inventario de un producto.
 *
 * @param id identificador único del registro de inventario
 * @param productoId identificador único del producto asociado
 * @param codigoProducto código único del producto asociado
 * @param nombreProducto nombre descriptivo del producto asociado
 * @param cantidadDisponible cantidad actual disponible en almacén
 * @param stockMinimo umbral mínimo permitido antes de emitir alerta de stock bajo
 * @param ultimaActualizacion fecha y hora de la última modificación del registro de inventario
 */
public record StockInventarioResponse(
    Long id,
    Long productoId,
    String codigoProducto,
    String nombreProducto,
    Integer cantidadDisponible,
    Integer stockMinimo,
    LocalDateTime ultimaActualizacion
) {
}