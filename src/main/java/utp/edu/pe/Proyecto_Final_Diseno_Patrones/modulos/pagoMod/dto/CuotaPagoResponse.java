package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.dto;

import java.time.LocalDate;

/**
 * DTO de respuesta con la información detallada de una cuota de pago.
 *
 * @param id identificador único de la cuota
 * @param numeroCuota número correlativo de la cuota dentro del cronograma
 * @param monto importe asignado a la cuota
 * @param fechaVencimiento fecha límite de pago de la cuota
 * @param pagado indica si la cuota ya fue pagada
 */
public record CuotaPagoResponse(
    Long id,
    Integer numeroCuota,
    Double monto,
    LocalDate fechaVencimiento,
    Boolean pagado) {
}
