package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.dto;

import java.math.BigDecimal;

/**
 * DTO de respuesta con la información detallada de un ítem del comprobante de pago.
 *
 * @param id identificador único del detalle de comprobante
 * @param productoId identificador único del producto asociado
 * @param codigoProducto código comercial o SKU del producto
 * @param nombreProducto nombre o descripción del producto
 * @param cantidad cantidad de unidades facturadas
 * @param precioUnitario precio unitario aplicado al producto
 * @param subtotal importe subtotal calculado para este ítem
 */
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
