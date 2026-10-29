package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.TipoComprobante;

import java.util.List;

public record CrearComprobanteRequest(
        @NotNull(message = "El tipo de comprobante es obligatorio")
        TipoComprobante tipo,

        @NotBlank(message = "La serie es obligatoria")
        @Pattern(regexp = "[A-Za-z0-9]{4}", message = "La serie debe tener cuatro caracteres alfanuméricos")
        String serie,

        @NotBlank(message = "El número es obligatorio")
        @Pattern(regexp = "\\d{1,8}", message = "El número debe contener entre uno y ocho dígitos")
        String numero,

        @NotNull(message = "El cliente es obligatorio")
        Long clienteId,

        @NotNull(message = "El vendedor es obligatorio")
        Long vendedorId,

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

