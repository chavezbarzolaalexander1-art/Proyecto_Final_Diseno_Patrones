package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.Categoria;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.repository.CategoriaRepository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.CategoriaService;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {
    private final CategoriaRepository categoriaRepository;
    /**
     * Registra una nueva categoría en la base de datos.
     * @param categoria Objeto Categoria a registrar.
     * @return La categoría persistida.
     */
    @Override
    @Transactional
    public Categoria registrarCategoria(Categoria categoria) {
        // Validar si la categoría ya existe por nombre
        categoriaRepository.findByNombre(categoria.getNombre()).ifPresent(c -> {
            throw new IllegalArgumentException("Ya existe una categoría con el nombre: " + categoria.getNombre());
        });

        return categoriaRepository.save(categoria);
    }

    /**
     * Recupera el listado completo de categorías.
     * @return Lista de categorías.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    /**
     * Busca una categoría por su identificador primario.
     * @param id Clave primaria de la categoría.
     * @return Un Optional con la categoría si fue encontrada.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    /**
     * Actualiza los datos de una categoría existente.
     * @param id Clave primaria de la categoría a actualizar.
     * @param categoriaDetalles Objeto con los nuevos valores a asignar.
     * @return La categoría actualizada.
     */
    @Override
    @Transactional
    public Categoria actualizarCategoria(Long id, Categoria categoriaDetalles) {
        // 1. Verificar existencia de la categoría
        Categoria categoriaExistente = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la categoría con ID: " + id));

        // 2. Mapear nuevos datos
        categoriaExistente.setNombre(categoriaDetalles.getNombre());
        categoriaExistente.setDescripcion(categoriaDetalles.getDescripcion());

        return categoriaRepository.save(categoriaExistente);
    }

    /**
     * Elimina una categoría por su ID.
     * @param id Clave primaria de la categoría a eliminar.
     */
    @Override
    @Transactional
    public void eliminarCategoria(Long id) {
        // Confirmar la existencia antes de eliminar
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la categoría con ID: " + id));

        categoriaRepository.delete(categoria);
    }
}

