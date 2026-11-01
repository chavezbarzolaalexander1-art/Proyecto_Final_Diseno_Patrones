package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.dto.AnulacionRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.service.AnulacionComprobanteService;

@RestController
@RequestMapping("/api/anulaciones")
@RequiredArgsConstructor
@Validated
public class AnulacionComprobanteController {

    private final AnulacionComprobanteService anulacionComprobanteService;

    @PostMapping("/comprobante/{comprobanteId}")
    public ResponseEntity<Void> anularComprobante(
            @PathVariable Long comprobanteId,
            @Valid @RequestBody AnulacionRequest request
    ) {
        anulacionComprobanteService.anularComprobante(comprobanteId, request);
        return ResponseEntity.noContent().build();
    }
}