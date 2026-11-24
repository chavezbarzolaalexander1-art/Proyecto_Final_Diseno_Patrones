package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.dto;

import java.time.LocalDateTime;

/**
 * DTO de respuesta con la información detallada de un pago registrado.
 *
 * @param id identificador único del pago
 * @param comprobanteId identificador único del comprobante asociado
 * @param metodoPago método utilizado para efectuar el pago
 * @param monto importe bruto del pago realizado
 * @param comision comisión aplicada al pago
 * @param montoNeto importe neto resultante tras descontar la comisión
 * @param numeroTransaccion número de operación o transacción asociada
 * @param fechaPago fecha y hora en que se registró el pago
 */
public record PagoResponse(
    Long id,
    Long comprobanteId,
    String metodoPago,
    Double monto,
    Double comision,
    Double montoNeto,
    String numeroTransaccion,
    LocalDateTime fechaPago
) {
}
