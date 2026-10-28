package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service;

import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.Producto;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz de servicio para la gestión integral de productos y su inventario asociado.
 * @author Alexander Chavez
 * @version 1.0
 */
public interface ProductoService {
    // Crear un producto recibiendo el objeto completo
    Producto registrarProducto(Producto producto);

    // Consultas
    List<Producto> listarTodos();

    Optional<Producto> buscarPorId(Long id);

    Optional<Producto> buscarPorCodigo(String codigo);

    // Actualización de producto
    Producto actualizarProducto(Long id, Producto productoDetalles);

    // Eliminación de producto
    void eliminarProducto(Long id);
}
