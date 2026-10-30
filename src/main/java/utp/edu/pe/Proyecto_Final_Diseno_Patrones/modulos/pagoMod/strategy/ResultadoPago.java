package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.strategy;

/**
 * Resultado del procesamiento de un pago.
 *
 * @param metodo    medio de pago utilizado
 * @param monto     monto cobrado
 * @param comision  comisión descontada
 * @param montoNeto monto cobrado menos la comisión
 */
public record ResultadoPago(String metodo, double monto, double comision, double montoNeto) {
}
