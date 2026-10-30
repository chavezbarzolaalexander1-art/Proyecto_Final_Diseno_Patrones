package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.strategy;

import org.springframework.stereotype.Component;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model.Pago;

/** Estrategia para transferencias bancarias: exige número de operación y no genera comisión. */
@Component
public class PagoTransferencia implements EstrategiaPago {

    @Override
    public String metodo() {
        return "TRANSFERENCIA";
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
