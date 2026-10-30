package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.strategy;

import org.springframework.stereotype.Component;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model.Pago;

/** Estrategia para cobros con tarjeta: exige número de operación y aplica una comisión porcentual. */
@Component
public class PagoTarjeta implements EstrategiaPago {

    private static final double COMISION = 0.035;

    @Override
    public String metodo() {
        return "TARJETA";
    }

    @Override
    public void validar(Pago pago) {
        ValidacionPago.exigirNumeroTransaccion(pago, metodo());
    }

    @Override
    public double calcularComision(double monto) {
        return monto * COMISION;
    }
}
