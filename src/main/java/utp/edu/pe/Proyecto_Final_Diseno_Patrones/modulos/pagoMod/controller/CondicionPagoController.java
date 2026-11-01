package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model.CondicionPago;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.service.CondicionPagoService;

import java.util.List;

@RestController
@RequestMapping("/api/condiciones-pago")
@RequiredArgsConstructor
public class CondicionPagoController {

    private final CondicionPagoService condicionPagoService;

    @GetMapping
    public ResponseEntity<List<CondicionPago>> listarTodas() {
        return ResponseEntity.ok(condicionPagoService.listarTodas());
    }

    @PostMapping
    public ResponseEntity<CondicionPago> obtenerOCrear(@RequestParam String descripcion) {
        return ResponseEntity.ok(condicionPagoService.obtnerOCrear(descripcion));
    }
}