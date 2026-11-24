package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto;

/**
 * DTO de respuesta con la información de una empresa.
 *
 * @param id Identificador de la empresa.
 * @param ruc RUC de la empresa.
 * @param razonSocial Razón social de la empresa.
 * @param direccionFiscal Dirección fiscal de la empresa.
 * @param telefono Teléfono de la empresa.
 */
public record EmpresaResponse(
    Long id,
    String ruc,
    String razonSocial,
    String direccionFiscal,
    String telefono
) {
}
