package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.Producto;

/**
 * Repositorio JPA encargado de gestionar el acceso a datos de la entidad {@link Producto}.
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

  /**
   * Busca un producto por su código único de catálogo (por ejemplo, "BOT-500ML").
   *
   * @param codigo código único identificador del producto
   * @return un {@link Optional} que contiene el producto si existe, o vacío en caso contrario
   */
  Optional<Producto> findByCodigo(String codigo);

  /**
   * Obtiene la lista de todos los productos asociados a una categoría específica.
   *
   * @param categoriaId identificador único de la categoría
   * @return lista de productos pertenecientes a la categoría indicada
   */
  List<Producto> findByCategoriaId(Long categoriaId);

  /**
   * Verifica si existe un producto registrado con el código especificado.
   *
   * @param codigo código único del producto a validar
   * @return {@code true} si ya existe un producto con dicho código.
   */
  boolean existsByCodigo(String codigo);
}