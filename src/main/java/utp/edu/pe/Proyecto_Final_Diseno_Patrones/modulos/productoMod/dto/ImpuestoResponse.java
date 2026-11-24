package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto;

/**
 * DTO de respuesta con los datos registrados de un impuesto aplicable.
 *
 * @param id identificador único del impuesto
 * @param nombre nombre descriptivo del impuesto
 * @param porcentaje valor porcentual del impuesto
 */
public record ImpuestoResponse(
    Long id,
    String nombre,
    Double porcentaje
) {
}
