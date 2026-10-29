package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.dto;

import java.math.BigDecimal;

public record DetalleComprobanteResponse(
        Long id,
        Long productoId,
        String codigoProducto,
        String nombreProducto,
        Integer cantidad,
        BigDecimal precioUnitario,
        BigDecimal subtotal
) {
}
