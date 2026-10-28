package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.Impuesto;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.repository.ImpuestoRepository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.ImpuestoService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImpuestoServiceImpl implements ImpuestoService {
    private final ImpuestoRepository impuestoRepository;

    /**
     * Registra un nuevo tipo de impuesto en el sistema.
     * @param impuesto Objeto Impuesto a registrar.
     * @return El impuesto persistido.
     */
    @Override
    @Transactional
    public Impuesto registrarImpuesto(Impuesto impuesto) {
        // Validar no duplicar el nombre del impuesto
        impuestoRepository.findByNombre(impuesto.getNombre()).ifPresent(i -> {
            throw new IllegalArgumentException("Ya existe un impuesto con el nombre: " + impuesto.getNombre());
        });

        return impuestoRepository.save(impuesto);
    }

    /**
     * Recupera el listado completo de impuestos.
     * @return Lista de impuestos registrados.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Impuesto> listarTodos() {
        return impuestoRepository.findAll();
    }

    /**
     * Busca un impuesto por su ID.
     * @param id Clave primaria del impuesto.
     * @return Un Optional con el impuesto si fue encontrado.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Impuesto> buscarPorId(Long id) {
        return impuestoRepository.findById(id);
    }

    /**
     * Actualiza la información y porcentaje de un impuesto existente.
     * @param id Clave primaria del impuesto a actualizar.
     * @param impuestoDetalles Objeto con los nuevos datos.
     * @return El impuesto actualizado.
     */
    @Override
    @Transactional
    public Impuesto actualizarImpuesto(Long id, Impuesto impuestoDetalles) {
        // 1. Buscar si el impuesto existe
        Impuesto impuestoExistente = impuestoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el impuesto con ID: " + id));

        // 2. Mapear nuevos valores
        impuestoExistente.setNombre(impuestoDetalles.getNombre());
        impuestoExistente.setPorcentaje(impuestoDetalles.getPorcentaje());

        return impuestoRepository.save(impuestoExistente);
    }

    /**
     * Elimina un impuesto por su ID.
     * @param id Clave primaria del impuesto a eliminar.
     */
    @Override
    @Transactional
    public void eliminarImpuesto(Long id) {
        // Verificar existencia previa
        Impuesto impuesto = impuestoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el impuesto con ID: " + id));

        impuestoRepository.delete(impuesto);
    }
}
