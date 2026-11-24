package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto;

/**
 * DTO de respuesta con los datos registrados de una categoría de productos.
 *
 * @param id identificador único de la categoría
 * @param nombre nombre de la categoría
 * @param descripcion descripción detallada de la categoría
 */
public record CategoriaResponse(
    Long id,
    String nombre,
    String descripcion
) {
}