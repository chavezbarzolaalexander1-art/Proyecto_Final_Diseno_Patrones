package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.strategy;

import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model.Pago;

/**
 * Patrón Strategy: contrato común de los algoritmos de procesamiento de pago.
 * Cada medio de pago (efectivo, tarjeta, transferencia, Yape/Plin) implementa
 * sus propias validaciones y su cálculo de comisión.
 */
public interface EstrategiaPago {

    /** Nombre del MetodoPago que atiende esta estrategia (por ejemplo, "TARJETA"). */
    String metodo();

    /** Valida las reglas propias del medio de pago; lanza IllegalArgumentException si no se cumplen. */
    void validar(Pago pago);

    /** Calcula la comisión que se descuenta del monto cobrado. */
    double calcularComision(double monto);
}
