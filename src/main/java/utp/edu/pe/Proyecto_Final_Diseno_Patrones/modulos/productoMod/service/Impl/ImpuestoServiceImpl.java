package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.ImpuestoRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.ImpuestoResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.mapper.ImpuestoMapper;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.Impuesto;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.repository.ImpuestoRepository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.ImpuestoService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImpuestoServiceImpl implements ImpuestoService {
    private final ImpuestoRepository impuestoRepository;
    private final ImpuestoMapper impuestoMapper;
    /**
     * Registra un nuevo tipo de impuesto en el sistema.
     * @param request Objeto Impuesto a registrar.
     * @return El impuesto persistido.
     */
    @Override
    @Transactional
    public ImpuestoResponse registrarImpuesto(ImpuestoRequest request) {
        impuestoRepository.findByNombre(request.nombre().trim()).ifPresent(i -> {
            throw new IllegalArgumentException("Ya existe un impuesto con el nombre: " + request.nombre());
        });
        Impuesto impuesto = impuestoMapper.toEntity(request);
        return impuestoMapper.toResponse(impuestoRepository.save(impuesto));
    }

    /**
     * Recupera el listado completo de impuestos.
     * @return Lista de impuestos registrados.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ImpuestoResponse> listarTodos() {
        return impuestoRepository.findAll().stream().map(impuestoMapper::toResponse).toList();
    }
    /**
     * Busca un impuesto por su ID.
     * @param id Clave primaria del impuesto.
     * @return Un Optional con el impuesto si fue encontrado.
     */
    @Override
    @Transactional(readOnly = true)
    public ImpuestoResponse buscarPorId(Long id) {
        Impuesto impuesto = impuestoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el impuesto con ID: " + id));
        return impuestoMapper.toResponse(impuesto);
    }
    /**
     * Actualiza la información y porcentaje de un impuesto existente.
     * @param id Clave primaria del impuesto a actualizar.
     * @param request Objeto con los nuevos datos.
     * @return El impuesto actualizado.
     */
    @Override
    @Transactional
    public ImpuestoResponse actualizarImpuesto(Long id, ImpuestoRequest request) {
        Impuesto existente = impuestoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el impuesto con ID: " + id));
        impuestoMapper.updateEntityFromRequest(request, existente);
        return impuestoMapper.toResponse(impuestoRepository.save(existente));
    }

    /**
     * Elimina un impuesto por su ID.
     * @param id Clave primaria del impuesto a eliminar.
     */
    @Override
    @Transactional
    public void eliminarImpuesto(Long id) {
        if (!impuestoRepository.existsById(id)) {
            throw new NoSuchElementException("No se encontró el impuesto con ID: " + id);
        }
        impuestoRepository.deleteById(id);
    }
}
