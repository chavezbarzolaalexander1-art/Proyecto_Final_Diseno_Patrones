package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto;

/**
 * DTO de respuesta con los datos registrados de una unidad de medida.
 *
 * @param id identificador único de la unidad de medida
 * @param codigo código único abreviado de la unidad de medida (por ejemplo, NIU, KGM)
 * @param descripcion descripción completa de la unidad de medida
 */
public record UnidadMedidaResponse(
    Long id,
    String codigo,
    String descripcion
) {
}