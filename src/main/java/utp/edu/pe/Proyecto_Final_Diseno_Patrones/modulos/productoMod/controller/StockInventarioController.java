package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.ActualizarStockRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.StockInventarioResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.StockInventarioService;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
@RequiredArgsConstructor
public class StockInventarioController {

    private final StockInventarioService stockInventarioService;

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<StockInventarioResponse> buscarPorProductoId(@PathVariable Long productoId) {
        return ResponseEntity.ok(stockInventarioService.buscarPorProductoId(productoId));
    }

    @PatchMapping("/producto/{productoId}")
    public ResponseEntity<StockInventarioResponse> actualizarCantidadStock(
            @PathVariable Long productoId,
            @Valid @RequestBody ActualizarStockRequest request
    ) {
        return ResponseEntity.ok(stockInventarioService.actualizarCantidadStock(productoId, request.cantidad()));
    }

    @GetMapping("/alertas/stock-bajo")
    public ResponseEntity<List<StockInventarioResponse>> listarProductosConStockBajo() {
        return ResponseEntity.ok(stockInventarioService.listarProductosConStockBajo());
    }
}