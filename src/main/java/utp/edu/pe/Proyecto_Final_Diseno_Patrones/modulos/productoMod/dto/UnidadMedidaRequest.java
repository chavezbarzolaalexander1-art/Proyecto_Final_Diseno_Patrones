package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UnidadMedidaRequest(
        @NotBlank(message = "El código de unidad de medida es obligatorio")
        @Size(max = 10, message = "El código no puede superar 10 caracteres")
        String codigo,

        @NotBlank(message = "La descripción es obligatoria")
        @Size(max = 50, message = "La descripción no puede superar 50 caracteres")
        String descripcion
) {
}