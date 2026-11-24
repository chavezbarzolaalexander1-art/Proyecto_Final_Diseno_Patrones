package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.ActualizarStockRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.StockInventarioResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.StockInventarioService;

/**
 * Controlador REST encargado de gestionar las operaciones de consulta y actualización del stock
 * de inventario.
 */
@RestController
@RequestMapping("/api/stock")
@RequiredArgsConstructor
public class StockInventarioController {

  private final StockInventarioService stockInventarioService;

  /**
   * Busca la información de stock de inventario asociada a un producto específico.
   *
   * @param productoId identificador único del producto
   * @return respuesta HTTP 200 (OK) con los datos del stock del producto
   */
  @GetMapping("/producto/{productoId}")
  public ResponseEntity<StockInventarioResponse> buscarPorProductoId(
      @PathVariable Long productoId) {
    return ResponseEntity.ok(stockInventarioService.buscarPorProductoId(productoId));
  }

  /**
   * Actualiza la cantidad disponible en stock de un producto específico.
   *
   * @param productoId identificador único del producto
   * @param request datos de solicitud con la nueva cantidad de stock
   * @return respuesta HTTP 200 (OK) con la información actualizada del stock
   */
  @PatchMapping("/producto/{productoId}")
  public ResponseEntity<StockInventarioResponse> actualizarCantidadStock(
      @PathVariable Long productoId,
      @Valid @RequestBody ActualizarStockRequest request) {
    return ResponseEntity.ok(
        stockInventarioService.actualizarCantidadStock(productoId, request.cantidad()));
  }

  /**
   * Obtiene la lista de productos que presentan niveles de stock por debajo del umbral mínimo.
   *
   * @return respuesta HTTP 200 (OK) con la lista de registros de stock bajo
   */
  @GetMapping("/alertas/stock-bajo")
  public ResponseEntity<List<StockInventarioResponse>> listarProductosConStockBajo() {
    return ResponseEntity.ok(stockInventarioService.listarProductosConStockBajo());
  }
}