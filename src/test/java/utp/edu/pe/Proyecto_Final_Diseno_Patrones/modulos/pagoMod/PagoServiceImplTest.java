package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.Boleta;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.ComprobantePago;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.EstadoComprobante;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.repository.ComprobantePagoRepository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.dto.PagoResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.dto.RegistrarPagoRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model.MetodoPago;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model.Pago;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.repository.MetodoPagoRepository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.repository.PagoRepository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.service.Impl.PagoServiceImpl;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.strategy.PagoEfectivo;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.strategy.PagoTarjeta;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.strategy.PagoTransferencia;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.strategy.PagoYapePlin;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.strategy.ProcesadorPago;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PagoServiceImplTest {

    @Mock
    private PagoRepository pagoRepository;

    @Mock
    private MetodoPagoRepository metodoPagoRepository;

    @Mock
    private ComprobantePagoRepository comprobanteRepository;

    private PagoServiceImpl service;

    @BeforeEach
    void crearServicio() {
        // El ProcesadorPago es real: se prueba la integración servicio + estrategias
        ProcesadorPago procesador = new ProcesadorPago(List.of(
                new PagoEfectivo(), new PagoTarjeta(), new PagoTransferencia(), new PagoYapePlin()));
        service = new PagoServiceImpl(pagoRepository, metodoPagoRepository, comprobanteRepository, procesador);
    }

    private ComprobantePago comprobante(String estado) {
        Boleta boleta = new Boleta();
        boleta.setId(1L);
        boleta.setEstado(EstadoComprobante.builder().nombre(estado).build());
        return boleta;
    }

    private void prepararGuardado() {
        when(pagoRepository.save(any(Pago.class))).thenAnswer(inv -> {
            Pago p = inv.getArgument(0);
            p.setId(10L);
            return p;
        });
    }

    @Test
    void registraUnPagoEnEfectivoSinComision() {
        when(comprobanteRepository.findById(1L)).thenReturn(Optional.of(comprobante("EMITIDO")));
        when(metodoPagoRepository.findByNombre("EFECTIVO"))
                .thenReturn(Optional.of(MetodoPago.builder().nombre("EFECTIVO").build()));
        prepararGuardado();

        PagoResponse respuesta = service.registrarPago(new RegistrarPagoRequest(1L, "efectivo", 100.0, null));

        assertEquals(10L, respuesta.id());
        assertEquals("EFECTIVO", respuesta.metodoPago());
        assertEquals(0.0, respuesta.comision());
        assertEquals(100.0, respuesta.montoNeto());
    }

    @Test
    void registraUnPagoConTarjetaAplicandoComision() {
        when(comprobanteRepository.findById(1L)).thenReturn(Optional.of(comprobante("EMITIDO")));
        when(metodoPagoRepository.findByNombre("TARJETA"))
                .thenReturn(Optional.of(MetodoPago.builder().nombre("TARJETA").build()));
        prepararGuardado();

        PagoResponse respuesta = service.registrarPago(new RegistrarPagoRequest(1L, "TARJETA", 200.0, "OP-1"));

        assertEquals(7.0, respuesta.comision(), 0.0001);
        assertEquals(193.0, respuesta.montoNeto(), 0.0001);
    }

    @Test
    void creaElMetodoDePagoSiNoExisteEnLaBaseDeDatos() {
        when(comprobanteRepository.findById(1L)).thenReturn(Optional.of(comprobante("EMITIDO")));
        when(metodoPagoRepository.findByNombre("YAPE/PLIN")).thenReturn(Optional.empty());
        when(metodoPagoRepository.save(any(MetodoPago.class))).thenAnswer(inv -> inv.getArgument(0));
        prepararGuardado();

        service.registrarPago(new RegistrarPagoRequest(1L, "YAPE/PLIN", 50.0, "YP-1"));

        verify(metodoPagoRepository).save(any(MetodoPago.class));
    }

    @Test
    void rechazaUnPagoQueNoCumpleLasReglasDelMedioDePago() {
        when(comprobanteRepository.findById(1L)).thenReturn(Optional.of(comprobante("EMITIDO")));
        when(metodoPagoRepository.findByNombre("TRANSFERENCIA"))
                .thenReturn(Optional.of(MetodoPago.builder().nombre("TRANSFERENCIA").build()));

        assertThrows(IllegalArgumentException.class,
                () -> service.registrarPago(new RegistrarPagoRequest(1L, "TRANSFERENCIA", 50.0, null)));
        verify(pagoRepository, never()).save(any());
    }

    @Test
    void rechazaUnMedioDePagoNoSoportado() {
        when(comprobanteRepository.findById(1L)).thenReturn(Optional.of(comprobante("EMITIDO")));

        assertThrows(IllegalArgumentException.class,
                () -> service.registrarPago(new RegistrarPagoRequest(1L, "CHEQUE", 50.0, "X")));
        verify(pagoRepository, never()).save(any());
    }

    @Test
    void rechazaPagosSobreUnComprobanteAnulado() {
        when(comprobanteRepository.findById(1L)).thenReturn(Optional.of(comprobante("ANULADO")));

        assertThrows(IllegalArgumentException.class,
                () -> service.registrarPago(new RegistrarPagoRequest(1L, "EFECTIVO", 50.0, null)));
        verify(pagoRepository, never()).save(any());
    }

    @Test
    void lanzaExcepcionSiElComprobanteNoExiste() {
        when(comprobanteRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> service.registrarPago(new RegistrarPagoRequest(99L, "EFECTIVO", 50.0, null)));
    }

    @Test
    void listaLosPagosDeUnComprobante() {
        Pago pago = Pago.builder().id(5L).monto(80.0).numeroTransaccion("T-1")
                .fechaPago(LocalDateTime.of(2026, 10, 28, 10, 0))
                .metodoPago(MetodoPago.builder().nombre("TRANSFERENCIA").build()).build();
        when(pagoRepository.findByComprobantePagoId(1L)).thenReturn(List.of(pago));

        List<PagoResponse> pagos = service.listarPorComprobante(1L);

        assertEquals(1, pagos.size());
        assertEquals("TRANSFERENCIA", pagos.get(0).metodoPago());
        assertEquals(80.0, pagos.get(0).monto());
    }

    @Test
    void calculaElTotalPagadoDeUnComprobante() {
        when(pagoRepository.findByComprobantePagoId(1L)).thenReturn(List.of(
                Pago.builder().monto(100.0).build(), Pago.builder().monto(50.5).build()));

        assertEquals(150.5, service.calcularTotalPagado(1L));
    }
}
