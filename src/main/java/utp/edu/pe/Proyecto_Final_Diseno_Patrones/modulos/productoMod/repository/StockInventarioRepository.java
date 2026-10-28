package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.StockInventario;

import java.util.List;
import java.util.Optional;
@Repository
public interface StockInventarioRepository extends JpaRepository<StockInventario, Long> {
    // Buscar el stock disponible directamente mediante el ID del producto
    Optional<StockInventario> findByProductoId(Long productoId);
    // Consultar productos cuyo stock disponible sea menor o igual al stock mínimo de seguridad
    @Query("SELECT s FROM StockInventario s WHERE s.cantidadDisponible <= s.stockMinimo")
    List<StockInventario> findProductosConStockBajo();
}
