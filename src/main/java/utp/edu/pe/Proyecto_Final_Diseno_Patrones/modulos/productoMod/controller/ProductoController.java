package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.ProductoRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.ProductoResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.ProductoService;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping
    public ResponseEntity<ProductoResponse> registrarProducto(@Valid @RequestBody ProductoRequest request) {
        return new ResponseEntity<>(productoService.registrarProducto(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductoResponse>> listarTodos() {
        return ResponseEntity.ok(productoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.buscarPorId(id));
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<ProductoResponse> buscarPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(productoService.buscarPorCodigo(codigo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponse> actualizarProducto(
            @PathVariable Long id,
            @Valid @RequestBody ProductoRequest request
    ) {
        return ResponseEntity.ok(productoService.actualizarProducto(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}