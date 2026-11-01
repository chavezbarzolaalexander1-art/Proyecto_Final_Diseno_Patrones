package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AnulacionRequest(
        @NotBlank(message = "El motivo de anulación es obligatorio")
        @Size(max = 255, message = "El motivo no puede superar 255 caracteres")
        String motivo
) {
}
