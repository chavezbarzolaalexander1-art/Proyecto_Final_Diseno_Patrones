package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.Impuesto;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.ImpuestoService;

import java.util.List;

@RestController
@RequestMapping("/api/impuestos")
@RequiredArgsConstructor
public class ImpuestoController {
    private final ImpuestoService impuestoService;

    /**
     * Registra un nuevo tipo de impuesto.
     *
     * @param impuesto Objeto Impuesto a crear.
     * @return El impuesto registrado con estado HTTP 201 Created.
     */
    @PostMapping
    public ResponseEntity<Impuesto> registrarImpuesto(@RequestBody Impuesto impuesto) {
        return new ResponseEntity<>(impuestoService.registrarImpuesto(impuesto), HttpStatus.CREATED);
    }

    /**
     * Obtiene todos los tipos de impuestos registrados.
     *
     * @return Lista de impuestos con estado HTTP 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<Impuesto>> listarTodos() {
        return ResponseEntity.ok(impuestoService.listarTodos());
    }

    /**
     * Busca un impuesto por su ID.
     *
     * @param id Identificador del impuesto.
     * @return El impuesto si fue encontrado con estado HTTP 200 OK o HTTP 404 Not Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Impuesto> buscarPorId(@PathVariable Long id) {
        return impuestoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Actualiza la información de un impuesto existente.
     *
     * @param id Identificador del impuesto a modificar.
     * @param impuesto Objeto Impuesto con los nuevos valores.
     * @return El impuesto actualizado con estado HTTP 200 OK.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Impuesto> actualizarImpuesto(@PathVariable Long id, @RequestBody Impuesto impuesto) {
        return ResponseEntity.ok(impuestoService.actualizarImpuesto(id, impuesto));
    }

    /**
     * Elimina un impuesto por su ID.
     *
     * @param id Identificador del impuesto a eliminar.
     * @return Respuesta vacía con estado HTTP 204 No Content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarImpuesto(@PathVariable Long id) {
        impuestoService.eliminarImpuesto(id);
        return ResponseEntity.noContent().build();
    }
}
