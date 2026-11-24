package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.UnidadMedidaRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.UnidadMedidaResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.mapper.UnidadMedidaMapper;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.UnidadMedida;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.repository.UnidadMedidaRepository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.UnidadMedidaService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UnidadMedidaServiceImpl implements UnidadMedidaService {
    private final UnidadMedidaRepository unidadMedidaRepository;
    private final UnidadMedidaMapper unidadMedidaMapper;
    /**
     * Registra una nueva unidad de medida.
     * @param request Objeto UnidadMedida a registrar.
     * @return La unidad de medida persistida.
     */
    @Override
    @Transactional
    public UnidadMedidaResponse registrarUnidadMedida(UnidadMedidaRequest request) {
        unidadMedidaRepository.findByCodigo(request.codigo().trim()).ifPresent(u -> {
            throw new IllegalArgumentException("Ya existe una unidad de medida con el código: " + request.codigo());
        });
        UnidadMedida unidad = unidadMedidaMapper.toEntity(request);
        return unidadMedidaMapper.toResponse(unidadMedidaRepository.save(unidad));
    }

    /**
     * Recupera el listado completo de unidades de medida.
     * @return Lista de unidades de medida.
     */
    @Override
    @Transactional(readOnly = true)
    public List<UnidadMedidaResponse> listarTodas() {
        return unidadMedidaRepository.findAll().stream().map(unidadMedidaMapper::toResponse).toList();
    }
    /**
     * Busca una unidad de medida por su ID.
     * @param id Clave primaria de la unidad de medida.
     * @return Un Optional con la unidad de medida si fue encontrada.
     */
    @Override
    @Transactional(readOnly = true)
    public UnidadMedidaResponse buscarPorId(Long id) {
        UnidadMedida unidad = unidadMedidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la unidad de medida con ID: " + id));
        return unidadMedidaMapper.toResponse(unidad);
    }
    /**
     * Actualiza la información de una unidad de medida.
     * @param id Clave primaria de la unidad a modificar.
     * @param request Nuevos datos a guardar.
     * @return La unidad de medida actualizada.
     */
    @Override
    @Transactional
    public UnidadMedidaResponse actualizarUnidadMedida(Long id, UnidadMedidaRequest request) {
        UnidadMedida existente = unidadMedidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la unidad de medida con ID: " + id));
        unidadMedidaMapper.updateEntityFromRequest(request, existente);
        return unidadMedidaMapper.toResponse(unidadMedidaRepository.save(existente));
    }

    /**
     * Elimina una unidad de medida por su ID.
     * @param id Clave primaria de la unidad a eliminar.
     */
    @Override
    @Transactional
    public void eliminarUnidadMedida(Long id) {
        if (!unidadMedidaRepository.existsById(id)) {
            throw new NoSuchElementException("No se encontró la unidad de medida con ID: " + id);
        }
        unidadMedidaRepository.deleteById(id);
    }
}
