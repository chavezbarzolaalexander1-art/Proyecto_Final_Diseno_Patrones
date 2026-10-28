package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.StockInventario;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.repository.StockInventarioRepository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.StockInventarioService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockInventarioServiceImpl implements StockInventarioService {
    private final StockInventarioRepository stockInventarioRepository;

    /**
     * Busca el registro de stock asociado a un producto mediante su ID.
     *
     * @param productoId Clave primaria del producto.
     * @return Un Optional con el stock de inventario.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<StockInventario> buscarPorProductoId(Long productoId) {
        return stockInventarioRepository.findByProductoId(productoId);
    }

    /**
     * Ajusta manualmente o vía compras/ventas la cantidad de unidades disponibles.
     *
     * @param productoId Clave primaria del producto.
     * @param nuevaCantidad Nueva cantidad total disponible.
     * @return El stock actualizado con fecha/hora actual.
     */
    @Override
    @Transactional
    public StockInventario actualizarCantidadStock(Long productoId, Integer nuevaCantidad) {
        // 1. Buscar el stock vinculado al producto
        StockInventario stock = stockInventarioRepository.findByProductoId(productoId)
                .orElseThrow(() -> new RuntimeException("No se encontró registro de stock para el producto ID: " + productoId));

        // 2. Asignar la cantidad y actualizar la estampa de tiempo
        stock.setCantidadDisponible(nuevaCantidad);
        stock.setUltimaActualizacion(LocalDateTime.now());

        return stockInventarioRepository.save(stock);
    }

    /**
     * Consulta los registros donde el stock disponible es menor o igual al stock mínimo.
     * @return Lista de registros de stock en estado crítico.
     */
    @Override
    @Transactional(readOnly = true)
    public List<StockInventario> listarProductosConStockBajo() {
        return stockInventarioRepository.findProductosConStockBajo();
    }
}
