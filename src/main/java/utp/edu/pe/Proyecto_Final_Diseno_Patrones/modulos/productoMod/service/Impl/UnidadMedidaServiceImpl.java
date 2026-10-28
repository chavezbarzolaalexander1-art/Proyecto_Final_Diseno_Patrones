package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.UnidadMedida;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.repository.UnidadMedidaRepository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.UnidadMedidaService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UnidadMedidaServiceImpl implements UnidadMedidaService {
    private final UnidadMedidaRepository unidadMedidaRepository;

    /**
     * Registra una nueva unidad de medida.
     * @param unidadMedida Objeto UnidadMedida a registrar.
     * @return La unidad de medida persistida.
     */
    @Override
    @Transactional
    public UnidadMedida registrarUnidadMedida(UnidadMedida unidadMedida) {
        // Validar no duplicar por código de unidad de medida
        unidadMedidaRepository.findByCodigo(unidadMedida.getCodigo()).ifPresent(u -> {
            throw new IllegalArgumentException("Ya existe una unidad de medida con el código: " + unidadMedida.getCodigo());
        });

        return unidadMedidaRepository.save(unidadMedida);
    }

    /**
     * Recupera el listado completo de unidades de medida.
     * @return Lista de unidades de medida.
     */
    @Override
    @Transactional(readOnly = true)
    public List<UnidadMedida> listarTodas() {
        return unidadMedidaRepository.findAll();
    }

    /**
     * Busca una unidad de medida por su ID.
     * @param id Clave primaria de la unidad de medida.
     * @return Un Optional con la unidad de medida si fue encontrada.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UnidadMedida> buscarPorId(Long id) {
        return unidadMedidaRepository.findById(id);
    }

    /**
     * Actualiza la información de una unidad de medida.
     * @param id Clave primaria de la unidad a modificar.
     * @param unidadMedidaDetalles Nuevos datos a guardar.
     * @return La unidad de medida actualizada.
     */
    @Override
    @Transactional
    public UnidadMedida actualizarUnidadMedida(Long id, UnidadMedida unidadMedidaDetalles) {
        // 1. Verificar la existencia
        UnidadMedida unidadExistente = unidadMedidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la unidad de medida con ID: " + id));

        // 2. Mapear nuevos atributos
        unidadExistente.setCodigo(unidadMedidaDetalles.getCodigo());
        unidadExistente.setDescripcion(unidadMedidaDetalles.getDescripcion());

        return unidadMedidaRepository.save(unidadExistente);
    }

    /**
     * Elimina una unidad de medida por su ID.
     * @param id Clave primaria de la unidad a eliminar.
     */
    @Override
    @Transactional
    public void eliminarUnidadMedida(Long id) {
        // Confirmar la existencia antes de borrar
        UnidadMedida unidad = unidadMedidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la unidad de medida con ID: " + id));

        unidadMedidaRepository.delete(unidad);
    }
}
