package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.TipoComprobante;

/**
 * DTO de solicitud para la emisión de un nuevo comprobante de pago.
 *
 * @param tipo tipo de comprobante de pago
 * @param serie serie alfanumérica de cuatro caracteres del comprobante
 * @param numero número correlativo del comprobante de uno a ocho dígitos
 * @param clienteId identificador único del cliente asociado
 * @param vendedorId identificador único del vendedor asociado
 * @param condicionPagoId identificador opcional de la condición de pago
 * @param detalles lista de detalles o ítems incluidos en el comprobante
 * @param observaciones observaciones adicionales sobre el comprobante
 * @param ordenCompra número de orden de compra asociada
 * @param guiaRemision número de guía de remisión asociada
 */
public record CrearComprobanteRequest(
    @NotNull(message = "El tipo de comprobante es obligatorio")
    TipoComprobante tipo,

    @NotBlank(message = "La serie es obligatoria")
    @Pattern(regexp = "[A-Za-z0-9]{4}", message = "La serie debe tener cuatro caracteres "
    + "alfanuméricos")
    String serie,

    @NotBlank(message = "El número es obligatorio")
    @Pattern(regexp = "\\d{1,8}", message = "El número debe contener entre uno y ocho dígitos")
    String numero,

    @NotNull(message = "El cliente es obligatorio")
    Long clienteId,

    @NotNull(message = "El vendedor es obligatorio")
    Long vendedorId,

    Long condicionPagoId,

    @NotEmpty(message = "El comprobante debe contener al menos un detalle")
    List<@Valid DetalleComprobanteRequest> detalles,

    @Size(max = 100, message = "Las observaciones no pueden superar 100 caracteres")
    String observaciones,

    @Size(max = 20, message = "La orden de compra no puede superar 20 caracteres")
    String ordenCompra,

    @Size(max = 200, message = "La guía de remisión no puede superar 200 caracteres")
    String guiaRemision
) {
}

