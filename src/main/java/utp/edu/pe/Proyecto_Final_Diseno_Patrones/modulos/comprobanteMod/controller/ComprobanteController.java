package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.dto.ComprobanteResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.dto.CrearComprobanteRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.service.ComprobanteService;

import java.util.List;

@RestController
@RequestMapping("/api/comprobantes")
@RequiredArgsConstructor
@Validated

public class ComprobanteController {
    private final ComprobanteService comprobanteService;

    @PostMapping
    public ResponseEntity<ComprobanteResponse> emitir(
            @Valid @RequestBody CrearComprobanteRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(comprobanteService.emitir(request));
    }

    @GetMapping
    public ResponseEntity<List<ComprobanteResponse>> listarTodos() {
        return ResponseEntity.ok(comprobanteService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComprobanteResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(comprobanteService.buscarPorId(id));
    }
}
