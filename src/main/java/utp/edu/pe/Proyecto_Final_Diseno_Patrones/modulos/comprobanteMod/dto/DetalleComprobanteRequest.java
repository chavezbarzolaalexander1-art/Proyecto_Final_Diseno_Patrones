package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * DTO de solicitud para representar un ítem o detalle dentro de un comprobante de pago.
 *
 * @param productoId identificador único del producto seleccionado
 * @param cantidad cantidad de unidades del producto
 */
public record DetalleComprobanteRequest(
    @NotNull(message = "El producto es obligatorio")
    Long productoId,

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor que cero")
    Integer cantidad
) {
}
