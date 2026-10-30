package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.strategy;

import org.springframework.stereotype.Component;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model.Pago;

/** Estrategia para billeteras digitales (Yape/Plin): exige número de operación y no genera comisión. */
@Component
public class PagoYapePlin implements EstrategiaPago {

    @Override
    public String metodo() {
        return "YAPE/PLIN";
    }

    @Override
    public void validar(Pago pago) {
        ValidacionPago.exigirNumeroTransaccion(pago, metodo());
    }

    @Override
    public double calcularComision(double monto) {
        return 0.0;
    }
}
