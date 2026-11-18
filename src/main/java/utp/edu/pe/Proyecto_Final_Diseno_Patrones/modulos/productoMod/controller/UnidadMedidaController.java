package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.UnidadMedidaRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.UnidadMedidaResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.UnidadMedidaService;

import java.util.List;

@RestController
@RequestMapping("/api/unidades-medida")
@RequiredArgsConstructor
public class UnidadMedidaController {

    private final UnidadMedidaService unidadMedidaService;

    @PostMapping
    public ResponseEntity<UnidadMedidaResponse> registrarUnidadMedida(@Valid @RequestBody UnidadMedidaRequest request) {
        return new ResponseEntity<>(unidadMedidaService.registrarUnidadMedida(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UnidadMedidaResponse>> listarTodas() {
        return ResponseEntity.ok(unidadMedidaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadMedidaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(unidadMedidaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnidadMedidaResponse> actualizarUnidadMedida(
            @PathVariable Long id,
            @Valid @RequestBody UnidadMedidaRequest request
    ) {
        return ResponseEntity.ok(unidadMedidaService.actualizarUnidadMedida(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUnidadMedida(@PathVariable Long id) {
        unidadMedidaService.eliminarUnidadMedida(id);
        return ResponseEntity.noContent().build();
    }
}