package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.StockInventario;


/**
 * Repositorio JPA encargado de gestionar el acceso a datos de la entidad {@link StockInventario}.
 */
@Repository
public interface StockInventarioRepository extends JpaRepository<StockInventario, Long> {

  /**
   * Busca el registro de stock disponible asociado directamente al identificador de un producto.
   *
   * @param productoId identificador único del producto
   * @return un {@link Optional} que contiene el registro de inventario si existe, o vacío en caso contrario
   */
  Optional<StockInventario> findByProductoId(Long productoId);

  /**
   * Consulta los registros de inventario cuya cantidad disponible es menor o igual al stock mínimo
   * establecido para emitir alertas de reposición.
   *
   * @return lista de registros de {@link StockInventario} con stock bajo
   */
  @Query("SELECT s FROM StockInventario s WHERE s.cantidadDisponible <= s.stockMinimo")
  List<StockInventario> findProductosConStockBajo();
}