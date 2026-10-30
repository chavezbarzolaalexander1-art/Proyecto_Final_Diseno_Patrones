package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.repository.PagoRepository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.service.PagoService;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.strategy.ProcesadorPago;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Verifica el patrón Singleton administrado por el contenedor IoC de Spring
 * para los componentes del módulo de pagos.
 */
@SpringBootTest
class PagoSingletonTest {

    @Autowired
    private ApplicationContext contexto;

    @Test
    void pagoServiceEsUnaUnicaInstancia() {
        assertSame(contexto.getBean(PagoService.class), contexto.getBean(PagoService.class));
        assertTrue(contexto.isSingleton("pagoServiceImpl"));
    }

    @Test
    void procesadorDePagoYRepositorioSonUnaUnicaInstancia() {
        assertSame(contexto.getBean(ProcesadorPago.class), contexto.getBean(ProcesadorPago.class));
        assertSame(contexto.getBean(PagoRepository.class), contexto.getBean(PagoRepository.class));
    }
}
