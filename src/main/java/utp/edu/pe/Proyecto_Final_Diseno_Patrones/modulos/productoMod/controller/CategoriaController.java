package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.CategoriaRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.CategoriaResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.CategoriaService;

/**
 * Controlador REST encargado de gestionar las operaciones CRUD de categorías de productos.
 */
@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

  private final CategoriaService categoriaService;

  /**
   * Registra una nueva categoría en el sistema.
   *
   * @param request datos de solicitud de la categoría a registrar
   * @return respuesta HTTP 201 (Created) con la información de la categoría creada
   */
  @PostMapping
  public ResponseEntity<CategoriaResponse> registrarCategoria(
      @Valid @RequestBody CategoriaRequest request) {
    return new ResponseEntity<>(categoriaService.registrarCategoria(request), HttpStatus.CREATED);
  }

  /**
   * Obtiene la lista completa de categorías registradas.
   *
   * @return respuesta HTTP 200 (OK) con la lista de categorías
   */
  @GetMapping
  public ResponseEntity<List<CategoriaResponse>> listarTodas() {
    return ResponseEntity.ok(categoriaService.listarTodas());
  }

  /**
   * Busca una categoría específica por su identificador único.
   *
   * @param id identificador único de la categoría
   * @return respuesta HTTP 200 (OK) con los datos de la categoría encontrada
   */
  @GetMapping("/{id}")
  public ResponseEntity<CategoriaResponse> buscarPorId(@PathVariable Long id) {
    return ResponseEntity.ok(categoriaService.buscarPorId(id));
  }

  /**
   * Actualiza los datos de una categoría existente.
   *
   * @param id identificador único de la categoría a actualizar
   * @param request nuevos datos de la categoría
   * @return respuesta HTTP 200 (OK) con la información actualizada de la categoría
   */
  @PutMapping("/{id}")
  public ResponseEntity<CategoriaResponse> actualizarCategoria(
      @PathVariable Long id,
      @Valid @RequestBody CategoriaRequest request) {
    return ResponseEntity.ok(categoriaService.actualizarCategoria(id, request));
  }

  /**
   * Elimina una categoría del sistema según su identificador único.
   *
   * @param id identificador único de la categoría a eliminar
   * @return respuesta HTTP 204 (No Content) tras completar la eliminación
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
    categoriaService.eliminarCategoria(id);
    return ResponseEntity.noContent().build();
  }
}