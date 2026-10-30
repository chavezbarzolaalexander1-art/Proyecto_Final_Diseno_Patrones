package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.strategy;

import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model.Pago;

/** Validaciones compartidas por las estrategias de pago. */
final class ValidacionPago {

    private ValidacionPago() {
    }

    static void exigirNumeroTransaccion(Pago pago, String metodo) {
        String numero = pago.getNumeroTransaccion();
        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException("El pago con " + metodo + " requiere número de transacción");
        }
    }
}
