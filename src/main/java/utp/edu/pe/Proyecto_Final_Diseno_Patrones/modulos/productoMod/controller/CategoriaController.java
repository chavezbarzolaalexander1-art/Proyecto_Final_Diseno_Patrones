// CategoriaController.java
package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.CategoriaRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.CategoriaResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.CategoriaService;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaResponse> registrarCategoria(@Valid @RequestBody CategoriaRequest request) {
        return new ResponseEntity<>(categoriaService.registrarCategoria(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> listarTodas() {
        return ResponseEntity.ok(categoriaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> actualizarCategoria(
            @PathVariable Long id,
            @Valid @RequestBody CategoriaRequest request
    ) {
        return ResponseEntity.ok(categoriaService.actualizarCategoria(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}