package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.Categoria;

/**
 * Repositorio JPA encargado de gestionar el acceso a datos de la entidad {@link Categoria}.
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
  /**
   * Busca una categoría por su nombre exacto para validar duplicados antes del registro.
   *
   * @param nombre nombre de la categoría a buscar
   * @return un {@link Optional} que contiene la categoría si existe, o vacío en caso contrario
   */
  Optional<Categoria> findByNombre(String nombre);
}
