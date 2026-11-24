package utp.edu.pe.Proyecto_Final_Diseno_Patrones.TestProyectoFinal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.ClienteRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.ClienteResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.DireccionDto;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.EmpresaRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.EmpresaResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.VendedorRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.VendedorResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model.TipoDocumento;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.service.ClienteService;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.service.EmpresaService;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.service.VendedorService;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class ClienteServiceTest {
  @Autowired
  private ClienteService clienteService;

  @Autowired
  private EmpresaService empresaService;

  @Autowired
  private VendedorService vendedorService;

  private ClienteRequest crearClienteRequest(String doc, String nombre) {
    return new ClienteRequest(
        nombre, doc, "correo@empresa.pe", "999111222",
        TipoDocumento.RUC, new DireccionDto("Av. Aviación 1200", "Lima", "Lima", "Surco")
    );
  }

  @Test
  void registrarClienteGuardaYRetornaId() {
    ClienteResponse guardado = clienteService.guardarCliente(crearClienteRequest("20445566771", "Comercial Sur SAC"));

    assertNotNull(guardado.id());
    assertEquals("Comercial Sur SAC", guardado.razonSocialONombre());
  }

  @Test
  void listarClientesRetornaListaConRegistros() {
    clienteService.guardarCliente(crearClienteRequest("20445566772", "Cliente Lista SAC"));

    assertFalse(clienteService.listarClientes().isEmpty());
  }

  @Test
  void actualizarClienteModificaDatosExistentes() {
    ClienteResponse guardado = clienteService.guardarCliente(crearClienteRequest("20445566773", "Nombre Inicial"));

    ClienteResponse actualizado = clienteService.actualizarCliente(
        guardado.id(), crearClienteRequest("20445566773", "Nombre Actualizado")
    );

    assertEquals("Nombre Actualizado", actualizado.razonSocialONombre());
  }

  @Test
  void eliminarClienteBorraRegistroYLanzaErrorSiNoExiste() {
    ClienteResponse guardado = clienteService.guardarCliente(crearClienteRequest("20445566774", "Cliente Eliminar"));
    Long clienteId = guardado.id();

    clienteService.eliminarCliente(clienteId);

    assertThrows(RuntimeException.class, () -> clienteService.buscarPorId(clienteId));
    assertThrows(NoSuchElementException.class, () -> clienteService.eliminarCliente(99999L));
  }

  @Test
  void crudEmpresaYValidacionNoEncontrado() {
    EmpresaResponse empresa = empresaService.guardarEmpresa(new EmpresaRequest(
        "20609988776", "Envases Industriales S.A.C.", "Av. Separadora Industrial 450", "014567890"
    ));
    Long empresaId = empresa.id();

    assertEquals("20609988776", empresaService.buscarPorId(empresaId).ruc());
    assertFalse(empresaService.listarEmpresas().isEmpty());

    EmpresaResponse editada = empresaService.actualizarEmpresa(empresaId, new EmpresaRequest(
        "20609988776", "Envases Perú S.A.C.", "Ate, Lima", "014567899"
    ));
    assertEquals("Envases Perú S.A.C.", editada.razonSocial());

    empresaService.eliminarEmpresa(empresaId);
    assertThrows(NoSuchElementException.class, () -> empresaService.eliminarEmpresa(empresaId));
  }

  @Test
  void crudVendedorYValidacionNoEncontrado() {
    VendedorResponse vendedor = vendedorService.guardarVendedor(
        new VendedorRequest("Carlos", "Mendoza", "45678911", "V-01")
    );
    Long vendedorId = vendedor.id();

    assertEquals("Carlos", vendedorService.buscarPorId(vendedorId).nombres());
    assertFalse(vendedorService.listarVendedores().isEmpty());

    VendedorResponse editado = vendedorService.actualizarVendedor(
        vendedorId, new VendedorRequest("Carlos Alberto", "Mendoza", "45678911", "V-01")
    );
    assertEquals("Carlos Alberto", editado.nombres());

    vendedorService.eliminarVendedor(vendedorId);
    assertThrows(NoSuchElementException.class, () -> vendedorService.eliminarVendedor(vendedorId));
  }
}