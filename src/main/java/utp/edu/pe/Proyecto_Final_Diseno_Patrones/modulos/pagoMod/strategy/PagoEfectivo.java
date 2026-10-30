package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.strategy;

import org.springframework.stereotype.Component;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model.Pago;

/** Estrategia para cobros en efectivo: no requiere número de operación ni genera comisión. */
@Component
public class PagoEfectivo implements EstrategiaPago {

    @Override
    public String metodo() {
        return "EFECTIVO";
    }

    @Override
    public void validar(Pago pago) {
        // El efectivo no tiene reglas adicionales.
    }

    @Override
    public double calcularComision(double monto) {
        return 0.0;
    }
}
