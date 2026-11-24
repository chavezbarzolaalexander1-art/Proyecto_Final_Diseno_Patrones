package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.ProductoRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.ProductoResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.ProductoService;

/**
 * Controlador REST encargado de gestionar las operaciones CRUD de productos.
 */
@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

  private final ProductoService productoService;

  /**
   * Registra un nuevo producto en el sistema.
   *
   * @param request datos de solicitud del producto a registrar
   * @return respuesta HTTP 201 (Created) con la información del producto creado
   */
  @PostMapping
  public ResponseEntity<ProductoResponse> registrarProducto(
      @Valid @RequestBody ProductoRequest request) {
    return new ResponseEntity<>(productoService.registrarProducto(request), HttpStatus.CREATED);
  }

  /**
   * Obtiene la lista completa de productos registrados.
   *
   * @return respuesta HTTP 200 (OK) con la lista de productos
   */
  @GetMapping
  public ResponseEntity<List<ProductoResponse>> listarTodos() {
    return ResponseEntity.ok(productoService.listarTodos());
  }

  /**
   * Busca un producto específico por su identificador único.
   *
   * @param id identificador único del producto
   * @return respuesta HTTP 200 (OK) con los datos del producto encontrado
   */
  @GetMapping("/{id}")
  public ResponseEntity<ProductoResponse> buscarPorId(@PathVariable Long id) {
    return ResponseEntity.ok(productoService.buscarPorId(id));
  }

  /**
   * Busca un producto específico por su código único.
   *
   * @param codigo código único asignado al producto
   * @return respuesta HTTP 200 (OK) con los datos del producto encontrado
   */
  @GetMapping("/codigo/{codigo}")
  public ResponseEntity<ProductoResponse> buscarPorCodigo(@PathVariable String codigo) {
    return ResponseEntity.ok(productoService.buscarPorCodigo(codigo));
  }

  /**
   * Actualiza los datos de un producto existente.
   *
   * @param id identificador único del producto a actualizar
   * @param request nuevos datos del producto
   * @return respuesta HTTP 200 (OK) con la información actualizada del producto
   */
  @PutMapping("/{id}")
  public ResponseEntity<ProductoResponse> actualizarProducto(
      @PathVariable Long id,
      @Valid @RequestBody ProductoRequest request) {
    return ResponseEntity.ok(productoService.actualizarProducto(id, request));
  }

  /**
   * Elimina un producto del sistema según su identificador único.
   *
   * @param id identificador único del producto a eliminar
   * @return respuesta HTTP 204 (No Content) tras completar la eliminación
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
    productoService.eliminarProducto(id);
    return ResponseEntity.noContent().build();
  }
}