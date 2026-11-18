package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.StockInventarioResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.mapper.ProductoMapper;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.StockInventario;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.repository.StockInventarioRepository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.StockInventarioService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockInventarioServiceImpl implements StockInventarioService {
    private static final ZoneId ZONA_LIMA = ZoneId.of("America/Lima");
    private final StockInventarioRepository stockInventarioRepository;
    private final ProductoMapper productoMapper;

    /**
     * Busca el registro de stock asociado a un producto mediante su ID.
     *
     * @param productoId Clave primaria del producto.
     * @return Un Optional con el stock de inventario.
     */
    @Override
    @Transactional(readOnly = true)
    public StockInventarioResponse buscarPorProductoId(Long productoId) {
        return productoMapper.toStockResponse(obtenerStockOError(productoId));
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
    public StockInventarioResponse actualizarCantidadStock(Long productoId, Integer nuevaCantidad) {
        if (nuevaCantidad == null || nuevaCantidad < 0) {
            throw new IllegalArgumentException("La nueva cantidad de stock no puede ser negativa.");
        }
        StockInventario stock = obtenerStockOError(productoId);
        stock.setCantidadDisponible(nuevaCantidad);
        stock.setUltimaActualizacion(LocalDateTime.now(ZONA_LIMA)
        );

        return productoMapper.toStockResponse(stockInventarioRepository.save(stock));
    }

    /**
     * Consulta los registros donde el stock disponible es menor o igual al stock mínimo.
     * @return Lista de registros de stock en estado crítico.
     */
    @Override
    @Transactional(readOnly = true)
    public List<StockInventarioResponse> listarProductosConStockBajo() {
        return stockInventarioRepository.findProductosConStockBajo().stream()
                .map(productoMapper::toStockResponse)
                .toList();
    }
    @Override
    @Transactional
    public void descontarStock(Long productoId, Integer cantidad) {
        StockInventario stock = obtenerStockOError(productoId);
        if (stock.getCantidadDisponible() < cantidad) {
            throw new IllegalArgumentException(
                    "Stock insuficiente para el producto ID: " + productoId
                            + ". Disponible: " + stock.getCantidadDisponible()
            );
        }
        stock.setCantidadDisponible(stock.getCantidadDisponible() - cantidad);
        stock.setUltimaActualizacion(LocalDateTime.now(ZONA_LIMA));
        stockInventarioRepository.save(stock);
    }

    @Override
    @Transactional
    public void restaurarStock(Long productoId, Integer cantidad) {
        StockInventario stock = obtenerStockOError(productoId);
        stock.setCantidadDisponible(stock.getCantidadDisponible() + cantidad);
        stock.setUltimaActualizacion(LocalDateTime.now(ZONA_LIMA));
        stockInventarioRepository.save(stock);
    }

    private StockInventario obtenerStockOError(Long productoId) {
        return stockInventarioRepository.findByProductoId(productoId)
                .orElseThrow(() -> new RuntimeException("No se encontró stock para el producto ID: " + productoId));
    }
}
