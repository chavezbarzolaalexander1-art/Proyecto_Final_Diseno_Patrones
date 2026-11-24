package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto;

/**
 * DTO de solicitud para registrar un vendedor.
 *
 * @param nombres Nombres del vendedor.
 * @param apellidos Apellidos del vendedor.
 * @param dni Documento Nacional de Identidad del vendedor.
 * @param codigoVendedor Código único del vendedor.
 */
public record VendedorRequest(
    String nombres,
    String apellidos,
    String dni,
    String codigoVendedor
) {
}
