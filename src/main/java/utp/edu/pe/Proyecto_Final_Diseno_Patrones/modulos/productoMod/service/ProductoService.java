package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.ProductoRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.ProductoResponse;

import java.util.List;

public interface ProductoService {
    ProductoResponse registrarProducto(ProductoRequest request);
    List<ProductoResponse> listarTodos();
    ProductoResponse buscarPorId(Long id);
    ProductoResponse buscarPorCodigo(String codigo);
    ProductoResponse actualizarProducto(Long id, ProductoRequest request);
    void eliminarProducto(Long id);
}