package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.StockInventario;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.StockInventarioService;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
@RequiredArgsConstructor
public class StockInventarioController {
    private final StockInventarioService stockInventarioService;

    /**
     * Obtiene la información de stock asociada a un producto por su ID.
     *
     * @param productoId ID del producto.
     * @return El registro de stock correspondiente con estado HTTP 200 OK o HTTP 404 Not Found.
     */
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<StockInventario> buscarPorProductoId(@PathVariable Long productoId) {
        return stockInventarioService.buscarPorProductoId(productoId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Actualiza la cantidad física de unidades disponibles en el stock de un producto.
     * @param productoId ID del producto a ajustar.
     * @param cantidad Nueva cantidad total disponible.
     * @return El registro de stock actualizado con estado HTTP 200 OK.
     */
    @PatchMapping("/producto/{productoId}")
    public ResponseEntity<StockInventario> actualizarCantidadStock(@PathVariable Long productoId, @RequestParam Integer cantidad) {
        return ResponseEntity.ok(stockInventarioService.actualizarCantidadStock(productoId, cantidad));
    }

    /**
     * Obtiene el listado de productos que han alcanzado o sobrepasado su nivel de stock mínimo.
     * @return Lista de registros de stock en estado bajo o crítico con estado HTTP 200 OK.
     */
    @GetMapping("/alertas/stock-bajo")
    public ResponseEntity<List<StockInventario>> listarProductosConStockBajo() {
        return ResponseEntity.ok(stockInventarioService.listarProductosConStockBajo());
    }
}
