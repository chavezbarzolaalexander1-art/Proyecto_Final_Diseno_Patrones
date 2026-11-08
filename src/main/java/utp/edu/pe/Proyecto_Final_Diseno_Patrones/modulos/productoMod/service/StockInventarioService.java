package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service;

import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.StockInventario;

import java.util.List;
import java.util.Optional;

public interface StockInventarioService {
    Optional<StockInventario> buscarPorProductoId(Long productoId);

    StockInventario actualizarCantidadStock(Long productoId, Integer nuevaCantidad);

    List<StockInventario> listarProductosConStockBajo();

    // Métodos delegados para emisión y anulación
    void descontarStock(Long productoId, Integer cantidad);
    void restaurarStock(Long productoId, Integer cantidad);
}
