package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model.MetodoPago;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model.Pago;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.strategy.PagoEfectivo;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.strategy.PagoTarjeta;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.strategy.PagoTransferencia;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.strategy.PagoYapePlin;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.strategy.ProcesadorPago;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.strategy.ResultadoPago;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Pruebas del patrón Strategy aplicado a los medios de pago. */
class PagoStrategyTest {

    private ProcesadorPago procesador;

    @BeforeEach
    void crearProcesador() {
        procesador = new ProcesadorPago(List.of(
                new PagoEfectivo(), new PagoTarjeta(), new PagoTransferencia(), new PagoYapePlin()));
    }

    private Pago pago(String metodo, double monto, String numeroTransaccion) {
        return Pago.builder()
                .monto(monto)
                .numeroTransaccion(numeroTransaccion)
                .metodoPago(MetodoPago.builder().nombre(metodo).build())
                .build();
    }

    @Test
    void efectivoNoRequiereNumeroNiGeneraComision() {
        ResultadoPago resultado = procesador.procesar(pago("EFECTIVO", 100.0, null));

        assertEquals(0.0, resultado.comision());
        assertEquals(100.0, resultado.montoNeto());
    }

    @Test
    void tarjetaAplicaComisionPorcentual() {
        ResultadoPago resultado = procesador.procesar(pago("TARJETA", 200.0, "OP-001"));

        assertEquals(7.0, resultado.comision(), 0.0001);
        assertEquals(193.0, resultado.montoNeto(), 0.0001);
    }

    @Test
    void tarjetaSinNumeroDeTransaccionEsRechazada() {
        assertThrows(IllegalArgumentException.class, () -> procesador.procesar(pago("TARJETA", 50.0, null)));
    }

    @Test
    void transferenciaSinNumeroDeTransaccionEsRechazada() {
        assertThrows(IllegalArgumentException.class, () -> procesador.procesar(pago("TRANSFERENCIA", 50.0, " ")));
    }

    @Test
    void transferenciaConNumeroDeTransaccionEsAceptadaSinComision() {
        ResultadoPago resultado = procesador.procesar(pago("TRANSFERENCIA", 300.0, "TR-555"));

        assertEquals("TRANSFERENCIA", resultado.metodo());
        assertEquals(0.0, resultado.comision());
        assertEquals(300.0, resultado.montoNeto());
    }

    @Test
    void yapePlinConNumeroDeTransaccionEsAceptado() {
        ResultadoPago resultado = procesador.procesar(pago("YAPE/PLIN", 80.0, "YP-77"));

        assertEquals("YAPE/PLIN", resultado.metodo());
        assertEquals(80.0, resultado.montoNeto());
    }

    @Test
    void yapePlinSinNumeroDeTransaccionEsRechazado() {
        assertThrows(IllegalArgumentException.class, () -> procesador.procesar(pago("YAPE/PLIN", 80.0, null)));
    }

    @Test
    void metodoNoSoportadoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> procesador.procesar(pago("CHEQUE", 10.0, "X")));
    }

    @Test
    void pagoSinMetodoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> procesador.procesar(Pago.builder().monto(10.0).build()));
    }

    @Test
    void soportaSoloLosMediosRegistrados() {
        assertTrue(procesador.soporta("TARJETA"));
        assertFalse(procesador.soporta("CHEQUE"));
    }
}
