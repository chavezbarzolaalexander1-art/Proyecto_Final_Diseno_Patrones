package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto;

import jakarta.validation.constraints.*;

/**
 * DTO de solicitud para el registro y actualización de un producto.
 *
 * @param codigo código único identificador del producto
 * @param nombre nombre descriptivo del producto
 * @param precioUnitario precio de venta unitario del producto
 * @param categoriaId identificador único de la categoría asociada
 * @param unidadMedidaId identificador único de la unidad de medida asociada
 * @param impuestoId identificador único del impuesto aplicable
 * @param stockInicial cantidad inicial disponible en inventario
 * @param stockMinimo cantidad mínima permitida antes de generar alerta de reposición
 */
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