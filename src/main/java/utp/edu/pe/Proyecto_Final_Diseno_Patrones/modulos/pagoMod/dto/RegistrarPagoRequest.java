package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 * DTO de solicitud para registrar un nuevo pago asociado a un comprobante.
 *
 * @param comprobanteId identificador único del comprobante de pago
 * @param metodoPago método utilizado para realizar el pago
 * @param monto importe del pago a registrar
 * @param numeroTransaccion número de operación o transacción opcional
 */
public record RegistrarPagoRequest(
    @NotNull(message = "El comprobante es obligatorio")
    Long comprobanteId,

    @NotBlank(message = "El método de pago es obligatorio")
    @Size(max = 50, message = "El método de pago no puede superar 50 caracteres")
    String metodoPago,

    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser mayor a 0")
    Double monto,

    @Size(max = 100, message = "El número de transacción no puede superar 100 caracteres")
    String numeroTransaccion
) {
}
