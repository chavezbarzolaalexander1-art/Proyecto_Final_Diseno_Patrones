package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.Categoria;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.CategoriaService;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {
    private final CategoriaService categoriaService;

    /**
     * Registra una nueva categoría.
     * @param categoria Objeto Categoria a crear.
     * @return La categoría creada con estado HTTP 201 Created.
     */
    @PostMapping
    public ResponseEntity<Categoria> registrarCategoria(@RequestBody Categoria categoria) {
        return new ResponseEntity<>(categoriaService.registrarCategoria(categoria), HttpStatus.CREATED);
    }

    /**
     * Obtiene el listado completo de categorías.
     * @return Lista de categorías con estado HTTP 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<Categoria>> listarTodas() {
        return ResponseEntity.ok(categoriaService.listarTodas());
    }

    /**
     * Busca una categoría por su identificador primario.
     * @param id Identificador de la categoría.
     * @return La categoría si existe con estado HTTP 200 OK o HTTP 404 Not Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) {
        return categoriaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Actualiza una categoría existente.
     * @param id Identificador de la categoría a actualizar.
     * @param categoria Objeto Categoria con los nuevos datos.
     * @return La categoría actualizada con estado HTTP 200 OK.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.actualizarCategoria(id, categoria));
    }

    /**
     * Elimina una categoría por su ID.
     * @param id Identificador de la categoría a eliminar.
     * @return Respuesta vacía con estado HTTP 204 No Content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
