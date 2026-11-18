package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProductoRequest(
        @NotBlank(message = "El código del producto es obligatorio")
        @Size(max = 30, message = "El código no puede superar 30 caracteres")
        String codigo,

        @NotBlank(message = "El nombre del producto es obligatorio")
        @Size(max = 150, message = "El nombre no puede superar 150 caracteres")
        String nombre,

        @NotNull(message = "El precio unitario es obligatorio")
        @Positive(message = "El precio unitario debe ser mayor a 0")
        Double precioUnitario,

        @NotNull(message = "La categoría es obligatoria")
        Long categoriaId,

        @NotNull(message = "La unidad de medida es obligatoria")
        Long unidadMedidaId,

        @NotNull(message = "El impuesto es obligatorio")
        Long impuestoId,

        @Min(value = 0, message = "El stock inicial no puede ser negativo")
        Integer stockInicial,

        @Min(value = 0, message = "El stock mínimo no puede ser negativo")
        Integer stockMinimo
) {
}