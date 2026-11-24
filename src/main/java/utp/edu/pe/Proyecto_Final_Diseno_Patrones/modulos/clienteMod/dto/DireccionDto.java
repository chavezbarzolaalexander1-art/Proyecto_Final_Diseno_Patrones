package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto;

/**
 * DTO que representa la ubicación geográfica o dirección fiscal.
 *
 * @param direccion calle, avenida, jirón, número o referencia exacta del domicilio
 * @param departamento nombre del departamento o región
 * @param provincia nombre de la provincia
 * @param distrito nombre del distrito
 */
public record DireccionDto(
    String direccion,
    String departamento,
    String provincia,
    String distrito
) {
}
