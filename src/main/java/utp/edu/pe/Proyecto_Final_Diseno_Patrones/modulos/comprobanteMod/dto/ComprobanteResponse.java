package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.TipoComprobante;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model.CondicionPago;

/**
 * DTO de respuesta con los datos completos de un comprobante de pago emitido.
 *
 * @param id identificador único del comprobante
 * @param tipo tipo de comprobante de pago
 * @param serie serie del comprobante
 * @param numero número correlativo del comprobante
 * @param fechaEmision fecha y hora de emisión
 * @param subtotal monto base imponible sin impuestos
 * @param igv monto correspondiente al IGV
 * @param total importe total del comprobante
 * @param clienteId identificador del cliente asociado
 * @param cliente nombre o razón social del cliente
 * @param vendedorId identificador del vendedor asociado
 * @param vendedor nombre del vendedor
 * @param estadoComprobante estado actual del comprobante
 * @param estadoPago estado actual del pago
 * @param condicionPago condición de pago acordada
 * @param observaciones observaciones adicionales
 * @param ordenCompra número de orden de compra referenciada
 * @param guiaRemision número de guía de remisión referenciada
 * @param detalles lista de detalles o ítems del comprobante
 */
public record ComprobanteResponse(
    Long id,
    TipoComprobante tipo,
    String serie,
    String numero,
    LocalDateTime fechaEmision,
    BigDecimal subtotal,
    BigDecimal igv,
    BigDecimal total,
    Long clienteId,
    String cliente,
    Long vendedorId,
    String vendedor,
    String estadoComprobante,
    String estadoPago,
    CondicionPago condicionPago,
    String observaciones,
    String ordenCompra,
    String guiaRemision,
    List<DetalleComprobanteResponse> detalles
) {
}
