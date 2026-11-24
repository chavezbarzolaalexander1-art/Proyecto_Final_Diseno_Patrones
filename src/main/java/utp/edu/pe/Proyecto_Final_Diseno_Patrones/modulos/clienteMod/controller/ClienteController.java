package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.ClienteRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.ClienteResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.service.ClienteService;

/**
 * Controlador REST para gestionar las operaciones CRUD de clientes.
 */
@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {
  private final ClienteService clienteService;

  /**
   * Obtiene el listado completo de clientes registrados en el sistema.
   *
   * @return respuesta HTTP 200 (OK) con la lista de {@link ClienteResponse}
   */
  @GetMapping
  public ResponseEntity<List<ClienteResponse>> listar() {
    return ResponseEntity.ok(clienteService.listarClientes());
  }

  /**
   * Busca un cliente específico a partir de su identificador único.
   *
   * @param id identificador único del cliente
   * @return respuesta HTTP 200 (OK) con los datos del {@link ClienteResponse} encontrado
   */
  @GetMapping("/{id}")
  public ResponseEntity<ClienteResponse> buscarPorId(@PathVariable Long id) {
    return ResponseEntity.ok(clienteService.buscarPorId(id));
  }

  /**
   * Registra un nuevo cliente en el sistema.
   *
   * @param request datos de entrada del cliente a registrar
   * @return respuesta HTTP 201 (Created) con el {@link ClienteResponse} creado
   */
  @PostMapping
  public ResponseEntity<ClienteResponse> guardar(@RequestBody ClienteRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.guardarCliente(request));
  }

  /**
   * Actualiza los datos de un cliente existente.
   *
   * @param id identificador único del cliente a actualizar
   * @param request nuevos datos del cliente
   * @return respuesta HTTP 200 (OK) con el {@link ClienteResponse} actualizado
   */
  @PutMapping("/{id}")
  public ResponseEntity<ClienteResponse> actualizar(@PathVariable Long id,
                                                    @RequestBody ClienteRequest request) {
    return ResponseEntity.ok(clienteService.actualizarCliente(id, request));
  }

  /**
   * Elimina un cliente del sistema a partir de su identificador.
   *
   * @param id identificador único del cliente a eliminar
   * @return respuesta HTTP 204 (No Content) si la eliminación fue exitosa
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminar(@PathVariable Long id) {
    clienteService.eliminarCliente(id);
    return ResponseEntity.noContent().build();
  }
}
