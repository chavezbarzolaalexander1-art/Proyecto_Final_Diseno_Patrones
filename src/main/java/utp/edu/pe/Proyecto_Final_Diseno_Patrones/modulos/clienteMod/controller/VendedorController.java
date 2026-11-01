package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.VendedorRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.VendedorResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.service.VendedorService;

import java.util.List;

@RestController
@RequestMapping("/api/vendedores")
@RequiredArgsConstructor
public class VendedorController {
    private final VendedorService vendedorService;

    @GetMapping
    public ResponseEntity<List<VendedorResponse>> listar() {
        return ResponseEntity.ok(vendedorService.listarVendedores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendedorResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(vendedorService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<VendedorResponse> guardar(@RequestBody VendedorRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vendedorService.guardarVendedor(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendedorResponse> actualizar(@PathVariable Long id, @RequestBody VendedorRequest request) {
        return ResponseEntity.ok(vendedorService.actualizarVendedor(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        vendedorService.eliminarVendedor(id);
        return ResponseEntity.noContent().build();
    }
}
