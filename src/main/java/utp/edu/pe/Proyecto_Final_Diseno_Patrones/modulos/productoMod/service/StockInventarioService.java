package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service;

import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.StockInventarioResponse;

import java.util.List;

public interface StockInventarioService {
    StockInventarioResponse buscarPorProductoId(Long productoId);
    StockInventarioResponse actualizarCantidadStock(Long productoId, Integer nuevaCantidad);
    List<StockInventarioResponse> listarProductosConStockBajo();

    void descontarStock(Long productoId, Integer cantidad);
    void restaurarStock(Long productoId, Integer cantidad);
}