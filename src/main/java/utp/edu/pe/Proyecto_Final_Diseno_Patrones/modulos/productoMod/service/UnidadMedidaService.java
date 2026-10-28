package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service;

import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.UnidadMedida;

import java.util.List;
import java.util.Optional;

public interface UnidadMedidaService {
    UnidadMedida registrarUnidadMedida(UnidadMedida unidadMedida);

    List<UnidadMedida> listarTodas();

    Optional<UnidadMedida> buscarPorId(Long id);

    UnidadMedida actualizarUnidadMedida(Long id, UnidadMedida unidadMedidaDetalles);

    void eliminarUnidadMedida(Long id);
}
