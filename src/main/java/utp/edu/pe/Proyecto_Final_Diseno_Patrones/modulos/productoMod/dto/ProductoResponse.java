package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto;

/**
 * DTO de respuesta con los datos registrados de un producto y su información de inventario.
 *
 * @param id identificador único del producto
 * @param codigo código único identificador del producto
 * @param nombre nombre descriptivo del producto
 * @param precioUnitario precio de venta unitario del producto
 * @param categoriaId identificador único de la categoría asociada
 * @param categoriaNombre nombre de la categoría asociada
 * @param unidadMedidaId identificador único de la unidad de medida asociada
 * @param unidadMedidaCodigo código abreviado de la unidad de medida (por ejemplo, NIU, KGM)
 * @param unidadMedidaDescripcion descripción completa de la unidad de medida
 * @param impuestoId identificador único del impuesto aplicable
 * @param impuestoNombre nombre descriptivo del impuesto aplicable
 * @param impuestoPorcentaje valor porcentual del impuesto aplicable
 * @param cantidadDisponible cantidad actual disponible en stock
 * @param stockMinimo cantidad mínima permitida antes de generar alerta de reposición
 */
public record ProductoResponse(
    Long id,
    String codigo,
    String nombre,
    Double precioUnitario,
    Long categoriaId,
    String categoriaNombre,
    Long unidadMedidaId,
    String unidadMedidaCodigo,
    String unidadMedidaDescripcion,
    Long impuestoId,
    String impuestoNombre,
    Double impuestoPorcentaje,
    Integer cantidadDisponible,
    Integer stockMinimo
) {
}