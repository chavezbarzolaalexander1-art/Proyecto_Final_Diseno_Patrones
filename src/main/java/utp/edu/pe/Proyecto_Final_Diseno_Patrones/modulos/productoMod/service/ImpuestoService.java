package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service;

import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.Impuesto;

import java.util.List;
import java.util.Optional;

public interface ImpuestoService {
    Impuesto registrarImpuesto(Impuesto impuesto);

    List<Impuesto> listarTodos();

    Optional<Impuesto> buscarPorId(Long id);

    Impuesto actualizarImpuesto(Long id, Impuesto impuestoDetalles);

    void eliminarImpuesto(Long id);
}
