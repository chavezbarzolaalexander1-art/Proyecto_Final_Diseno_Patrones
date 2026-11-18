package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.CategoriaRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.CategoriaResponse;
import java.util.List;

public interface CategoriaService {
    CategoriaResponse registrarCategoria(CategoriaRequest request);
    List<CategoriaResponse> listarTodas();
    CategoriaResponse buscarPorId(Long id);
    CategoriaResponse actualizarCategoria(Long id, CategoriaRequest request);
    void eliminarCategoria(Long id);
}