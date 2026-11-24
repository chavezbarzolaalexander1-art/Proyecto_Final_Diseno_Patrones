package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto;

/**
 * DTO de solicitud con los datos requeridos para registrar o actualizar una empresa.
 *
 * @param ruc Registro Único de Contribuyentes (RUC) de la empresa
 * @param razonSocial denominación o razón social registrada
 * @param direccionFiscal dirección fiscal principal de la empresa
 * @param telefono número telefónico de contacto de la empresa
 */
public record EmpresaRequest(
    String ruc,
    String razonSocial,
    String direccionFiscal,
    String telefono
) {
}
