package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.dto;

import java.time.LocalDate;

public record CuotaPagoResponse(
        Long id,
        Integer numeroCuota,
        Double monto,
        LocalDate fechaVencimiento,
        Boolean pagado) {
}
