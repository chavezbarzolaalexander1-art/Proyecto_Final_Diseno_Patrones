package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ActualizarStockRequest(
        @NotNull(message = "La cantidad es obligatoria")
        @Min(value = 0, message = "La cantidad no puede ser negativa")
        Integer cantidad
) {
}