package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto;

/**
 * DTO de respuesta con la información de un vendedor.
 *
 * @param id Identificador del vendedor.
 * @param nombres Nombres del vendedor.
 * @param apellidos Apellidos del vendedor.
 * @param dni Documento Nacional de Identidad del vendedor.
 * @param codigoVendedor Código único del vendedor.
 */
public record VendedorResponse(
    Long id,
    String nombres,
    String apellidos,
    String dni,
    String codigoVendedor
) {
}
