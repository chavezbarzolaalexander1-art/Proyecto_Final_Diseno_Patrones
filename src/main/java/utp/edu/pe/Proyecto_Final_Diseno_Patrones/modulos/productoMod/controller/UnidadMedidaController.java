package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.UnidadMedidaRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.UnidadMedidaResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.UnidadMedidaService;

/**
 * Controlador REST encargado de gestionar las operaciones CRUD de unidades de medida.
 */
@RestController
@RequestMapping("/api/unidades-medida")
@RequiredArgsConstructor
public class UnidadMedidaController {

  private final UnidadMedidaService unidadMedidaService;

  /**
   * Registra una nueva unidad de medida en el sistema.
   *
   * @param request datos de solicitud de la unidad de medida a registrar
   * @return respuesta HTTP 201 (Created) con la información de la unidad de medida creada
   */
  @PostMapping
  public ResponseEntity<UnidadMedidaResponse> registrarUnidadMedida(
      @Valid @RequestBody UnidadMedidaRequest request) {
    return new ResponseEntity<>(
        unidadMedidaService.registrarUnidadMedida(request), HttpStatus.CREATED);
  }

  /**
   * Obtiene la lista completa de unidades de medida registradas.
   *
   * @return respuesta HTTP 200 (OK) con la lista de unidades de medida
   */
  @GetMapping
  public ResponseEntity<List<UnidadMedidaResponse>> listarTodas() {
    return ResponseEntity.ok(unidadMedidaService.listarTodas());
  }

  /**
   * Busca una unidad de medida específica por su identificador único.
   *
   * @param id identificador único de la unidad de medida
   * @return respuesta HTTP 200 (OK) con los datos de la unidad de medida encontrada
   */
  @GetMapping("/{id}")
  public ResponseEntity<UnidadMedidaResponse> buscarPorId(@PathVariable Long id) {
    return ResponseEntity.ok(unidadMedidaService.buscarPorId(id));
  }

  /**
   * Actualiza los datos de una unidad de medida existente.
   *
   * @param id identificador único de la unidad de medida a actualizar
   * @param request nuevos datos de la unidad de medida
   * @return respuesta HTTP 200 (OK) con la información actualizada de la unidad de medida
   */
  @PutMapping("/{id}")
  public ResponseEntity<UnidadMedidaResponse> actualizarUnidadMedida(
      @PathVariable Long id,
      @Valid @RequestBody UnidadMedidaRequest request) {
    return ResponseEntity.ok(unidadMedidaService.actualizarUnidadMedida(id, request));
  }

  /**
   * Elimina una unidad de medida del sistema según su identificador único.
   *
   * @param id identificador único de la unidad de medida a eliminar
   * @return respuesta HTTP 204 (No Content) tras completar la eliminación
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminarUnidadMedida(@PathVariable Long id) {
    unidadMedidaService.eliminarUnidadMedida(id);
    return ResponseEntity.noContent().build();
  }
}