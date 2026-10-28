package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.Producto;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {
    // Buscar producto por su código único de catálogo (Ej. "BOT-500ML")
    Optional<Producto> findByCodigo(String codigo);

    // Listar todos los productos pertenecientes a una categoría específica
    List<Producto> findByCategoriaId(Long categoriaId);
    boolean existsByCodigo(String codigo);
}
