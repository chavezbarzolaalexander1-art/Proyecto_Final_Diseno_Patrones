package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record ImpuestoRequest(
        @NotBlank(message = "El nombre del impuesto es obligatorio")
        @Size(max = 50, message = "El nombre no puede superar 50 caracteres")
        String nombre,

        @NotNull(message = "El porcentaje es obligatorio")
        @PositiveOrZero(message = "El porcentaje no puede ser negativo")
        Double porcentaje
) {
}