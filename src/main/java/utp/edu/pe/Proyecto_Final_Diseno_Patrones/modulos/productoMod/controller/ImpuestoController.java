package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.ImpuestoRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.ImpuestoResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.ImpuestoService;

/**
 * Controlador REST encargado de gestionar las operaciones CRUD de impuestos.
 */
@RestController
@RequestMapping("/api/impuestos")
@RequiredArgsConstructor
public class ImpuestoController {

  private final ImpuestoService impuestoService;

  /**
   * Registra un nuevo impuesto en el sistema.
   *
   * @param request datos de solicitud del impuesto a registrar
   * @return respuesta HTTP 201 (Created) con la información del impuesto creado
   */
  @PostMapping
  public ResponseEntity<ImpuestoResponse> registrarImpuesto(
      @Valid @RequestBody ImpuestoRequest request) {
    return new ResponseEntity<>(impuestoService.registrarImpuesto(request), HttpStatus.CREATED);
  }

  /**
   * Obtiene la lista completa de impuestos registrados.
   *
   * @return respuesta HTTP 200 (OK) con la lista de impuestos
   */
  @GetMapping
  public ResponseEntity<List<ImpuestoResponse>> listarTodos() {
    return ResponseEntity.ok(impuestoService.listarTodos());
  }

  /**
   * Busca un impuesto específico por su identificador único.
   *
   * @param id identificador único del impuesto
   * @return respuesta HTTP 200 (OK) con los datos del impuesto encontrado
   */
  @GetMapping("/{id}")
  public ResponseEntity<ImpuestoResponse> buscarPorId(@PathVariable Long id) {
    return ResponseEntity.ok(impuestoService.buscarPorId(id));
  }

  /**
   * Actualiza los datos de un impuesto existente.
   *
   * @param id identificador único del impuesto a actualizar
   * @param request nuevos datos del impuesto
   * @return respuesta HTTP 200 (OK) con la información actualizada del impuesto
   */
  @PutMapping("/{id}")
  public ResponseEntity<ImpuestoResponse> actualizarImpuesto(
      @PathVariable Long id,
      @Valid @RequestBody ImpuestoRequest request) {
    return ResponseEntity.ok(impuestoService.actualizarImpuesto(id, request));
  }

  /**
   * Elimina un impuesto del sistema según su identificador único.
   *
   * @param id identificador único del impuesto a eliminar
   * @return respuesta HTTP 204 (No Content) tras completar la eliminación
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminarImpuesto(@PathVariable Long id) {
    impuestoService.eliminarImpuesto(id);
    return ResponseEntity.noContent().build();
  }
}