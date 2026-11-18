// ImpuestoController.java
package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.ImpuestoRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.ImpuestoResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.ImpuestoService;

import java.util.List;

@RestController
@RequestMapping("/api/impuestos")
@RequiredArgsConstructor
public class ImpuestoController {

    private final ImpuestoService impuestoService;

    @PostMapping
    public ResponseEntity<ImpuestoResponse> registrarImpuesto(@Valid @RequestBody ImpuestoRequest request) {
        return new ResponseEntity<>(impuestoService.registrarImpuesto(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ImpuestoResponse>> listarTodos() {
        return ResponseEntity.ok(impuestoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImpuestoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(impuestoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImpuestoResponse> actualizarImpuesto(
            @PathVariable Long id,
            @Valid @RequestBody ImpuestoRequest request
    ) {
        return ResponseEntity.ok(impuestoService.actualizarImpuesto(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarImpuesto(@PathVariable Long id) {
        impuestoService.eliminarImpuesto(id);
        return ResponseEntity.noContent().build();
    }
}