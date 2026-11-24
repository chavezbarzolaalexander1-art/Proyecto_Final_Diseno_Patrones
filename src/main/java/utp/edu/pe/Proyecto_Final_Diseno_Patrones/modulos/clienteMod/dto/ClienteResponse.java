package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto;

import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model.TipoDocumento;

/**
 * DTO de respuesta con los datos detallados de un cliente registrado.
 *
 * @param id identificador único del cliente
 * @param razonSocialONombre razón social de la empresa o nombre completo de la persona natural
 * @param numeroDocumento número de documento de identidad (DNI, RUC, etc.)
 * @param email correo electrónico de contacto del cliente
 * @param telefono número telefónico de contacto
 * @param tipoDocumento tipo de documento de identidad ({@link TipoDocumento})
 * @param direccion dirección fiscal o domicilio del cliente ({@link DireccionDto})
 */
public record ClienteResponse(
    Long id,
    String razonSocialONombre,
    String numeroDocumento,
    String email,
    String telefono,
    TipoDocumento tipoDocumento,
    DireccionDto direccion
) {
}
