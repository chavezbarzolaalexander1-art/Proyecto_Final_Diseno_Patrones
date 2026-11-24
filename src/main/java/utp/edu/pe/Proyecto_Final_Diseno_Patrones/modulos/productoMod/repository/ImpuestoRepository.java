package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.Impuesto;

/**
 * Repositorio JPA encargado de gestionar el acceso a datos de la entidad {@link Impuesto}.
 */
@Repository
public interface ImpuestoRepository extends JpaRepository<Impuesto, Long> {
  /**
   * Busca un impuesto por su nombre exacto (por ejemplo, "IGV" o "Exonerado").
   *
   * @param nombre nombre del impuesto a buscar
   * @return un {@link Optional} que contiene el impuesto si existe, o vacío en caso contrario
   */
  Optional<Impuesto> findByNombre(String nombre);
}
