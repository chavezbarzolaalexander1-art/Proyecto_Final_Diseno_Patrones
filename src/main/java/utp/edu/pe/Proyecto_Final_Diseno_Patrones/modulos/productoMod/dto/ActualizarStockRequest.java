package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * DTO de solicitud para actualizar el stock de un producto.
 *
 * @param cantidad nueva cantidad de stock disponible (debe ser mayor o igual a cero)
 */
public record ActualizarStockRequest(
    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 0, message = "La cantidad no puede ser negativa")
    Integer cantidad
) {
}