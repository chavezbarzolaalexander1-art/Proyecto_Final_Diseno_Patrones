package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.UnidadMedida;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.UnidadMedidaService;

import java.util.List;

@RestController
@RequestMapping("/api/unidades-medida")
@RequiredArgsConstructor
public class UnidadMedidaController {
    private final UnidadMedidaService unidadMedidaService;

    /**
     * Registra una nueva unidad de medida.
     *
     * @param unidadMedida Objeto UnidadMedida a registrar.
     * @return La unidad creada con estado HTTP 201 Created.
     */
    @PostMapping
    public ResponseEntity<UnidadMedida> registrarUnidadMedida(@RequestBody UnidadMedida unidadMedida) {
        return new ResponseEntity<>(unidadMedidaService.registrarUnidadMedida(unidadMedida), HttpStatus.CREATED);
    }

    /**
     * Obtiene el listado completo de unidades de medida.
     * @return Lista de unidades de medida con estado HTTP 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<UnidadMedida>> listarTodas() {
        return ResponseEntity.ok(unidadMedidaService.listarTodas());
    }

    /**
     * Busca una unidad de medida por su ID.
     * @param id Identificador de la unidad de medida.
     * @return La unidad de medida si existe con estado HTTP 200 OK o HTTP 404 Not Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UnidadMedida> buscarPorId(@PathVariable Long id) {
        return unidadMedidaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Actualiza los datos de una unidad de medida existente.
     *
     * @param id Identificador de la unidad a actualizar.
     * @param unidadMedida Objeto UnidadMedida con los nuevos valores.
     * @return La unidad de medida actualizada con estado HTTP 200 OK.
     */
    @PutMapping("/{id}")
    public ResponseEntity<UnidadMedida> actualizarUnidadMedida(@PathVariable Long id, @RequestBody UnidadMedida unidadMedida) {
        return ResponseEntity.ok(unidadMedidaService.actualizarUnidadMedida(id, unidadMedida));
    }

    /**
     * Elimina una unidad de medida por su ID.
     * @param id Identificador de la unidad a eliminar.
     * @return Respuesta vacía con estado HTTP 204 No Content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUnidadMedida(@PathVariable Long id) {
        unidadMedidaService.eliminarUnidadMedida(id);
        return ResponseEntity.noContent().build();
    }
}
