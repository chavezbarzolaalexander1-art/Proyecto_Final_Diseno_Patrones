package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO de solicitud para el registro y actualización de una categoría de productos.
 *
 * @param nombre nombre único o descriptivo de la categoría
 * @param descripcion descripción detallada opcional de la categoría
 */
public record CategoriaRequest(
    @NotBlank(message = "El nombre de la categoría es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar 100 caracteres")
    String nombre,

    @Size(max = 255, message = "La descripción no puede superar 255 caracteres")
    String descripcion
) {
}