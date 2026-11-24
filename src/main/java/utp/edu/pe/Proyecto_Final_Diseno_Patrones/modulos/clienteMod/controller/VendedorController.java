package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.VendedorRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.VendedorResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.service.VendedorService;

/**
 * Controlador REST para gestionar las operaciones CRUD de vendedores.
 */
@RestController
@RequestMapping("/api/vendedores")
@RequiredArgsConstructor
public class VendedorController {
  private final VendedorService vendedorService;

  /**
   * Obtiene el listado completo de vendedores registrados en el sistema.
   *
   * @return respuesta HTTP 200 (OK) con la lista de {@link VendedorResponse}
   */
  @GetMapping
  public ResponseEntity<List<VendedorResponse>> listar() {
    return ResponseEntity.ok(vendedorService.listarVendedores());
  }

  /**
   * Busca un vendedor específico a partir de su identificador único.
   *
   * @param id identificador único del vendedor
   * @return respuesta HTTP 200 (OK) con los datos del {@link VendedorResponse} encontrado
   */
  @GetMapping("/{id}")
  public ResponseEntity<VendedorResponse> buscarPorId(@PathVariable Long id) {
    return ResponseEntity.ok(vendedorService.buscarPorId(id));
  }

  /**
   * Registra un nuevo vendedor en el sistema.
   *
   * @param request datos de entrada del vendedor a registrar
   * @return respuesta HTTP 201 (Created) con el {@link VendedorResponse} creado
   */
  @PostMapping
  public ResponseEntity<VendedorResponse> guardar(@RequestBody VendedorRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(vendedorService.guardarVendedor(request));
  }

  /**
   * Actualiza los datos de un vendedor existente.
   *
   * @param id identificador único del vendedor a actualizar
   * @param request nuevos datos del vendedor
   * @return respuesta HTTP 200 (OK) con el {@link VendedorResponse} actualizado
   */
  @PutMapping("/{id}")
  public ResponseEntity<VendedorResponse> actualizar(
      @PathVariable Long id, @RequestBody VendedorRequest request) {
    return ResponseEntity.ok(vendedorService.actualizarVendedor(id, request));
  }

  /**
   * Elimina un vendedor del sistema a partir de su identificador.
   *
   * @param id identificador único del vendedor a eliminar
   * @return respuesta HTTP 204 (No Content) si la eliminación fue exitosa
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminar(@PathVariable Long id) {
    vendedorService.eliminarVendedor(id);
    return ResponseEntity.noContent().build();
  }
}