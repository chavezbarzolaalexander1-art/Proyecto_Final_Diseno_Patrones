package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.CategoriaRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.CategoriaResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.mapper.CategoriaMapper;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.Categoria;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.repository.CategoriaRepository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.CategoriaService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {
    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;
    /**
     * Registra una nueva categoría en la base de datos.
     * @param request Objeto Categoria a registrar.
     * @return La categoría persistida.
     */
    @Override
    @Transactional
    public CategoriaResponse registrarCategoria(CategoriaRequest request) {
        categoriaRepository.findByNombre(request.nombre().trim()).ifPresent(c -> {
            throw new IllegalArgumentException("Ya existe una categoría con el nombre: " + request.nombre());
        });
        Categoria categoria = categoriaMapper.toEntity(request);
        return categoriaMapper.toResponse(categoriaRepository.save(categoria));
    }

    /**
     * Recupera el listado completo de categorías.
     * @return Lista de categorías.
     */
    @Override
    @Transactional(readOnly = true)
    public List<CategoriaResponse> listarTodas() {
        return categoriaRepository.findAll().stream().map(categoriaMapper::toResponse).toList();
    }
    /**
     * Busca una categoría por su identificador primario.
     * @param id Clave primaria de la categoría.
     * @return Un Optional con la categoría si fue encontrada.
     */
    @Override
    @Transactional(readOnly = true)
    public CategoriaResponse buscarPorId(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la categoría con ID: " + id));
        return categoriaMapper.toResponse(categoria);
    }
    /**
     * Actualiza los datos de una categoría existente.
     * @param id Clave primaria de la categoría a actualizar.
     * @param request Objeto con los nuevos valores a asignar.
     * @return La categoría actualizada.
     */
    @Override
    @Transactional
    public CategoriaResponse actualizarCategoria(Long id, CategoriaRequest request) {
        Categoria existente = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la categoría con ID: " + id));
        categoriaMapper.updateEntityFromRequest(request, existente);
        return categoriaMapper.toResponse(categoriaRepository.save(existente));
    }

    /**
     * Elimina una categoría por su ID.
     * @param id Clave primaria de la categoría a eliminar.
     */
    @Override
    @Transactional
    public void eliminarCategoria(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new NoSuchElementException("No se encontró la categoría con ID: " + id);
        }
        categoriaRepository.deleteById(id);
    }
}

