package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.UnidadMedida;

/**
 * Repositorio JPA encargado de gestionar el acceso a datos de la entidad {@link UnidadMedida}.
 */
@Repository
public interface UnidadMedidaRepository extends JpaRepository<UnidadMedida, Long> {
  /**
   * Busca una unidad de medida por su código estándar ,
   * de catálogo SUNAT (por ejemplo, "NIU", "ZZ", "KG").
   *
   * @param codigo código único de la unidad de medida a buscar
   * @return un {@link Optional} que contiene la unidad de medida si existe.
   */
  Optional<UnidadMedida> findByCodigo(String codigo);
}
