package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service;

import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    Categoria registrarCategoria(Categoria categoria);

    List<Categoria> listarTodas();

    Optional<Categoria> buscarPorId(Long id);

    Categoria actualizarCategoria(Long id, Categoria categoriaDetalles);

    void eliminarCategoria(Long id);
}
