package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.dto;

import java.time.LocalDateTime;

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
