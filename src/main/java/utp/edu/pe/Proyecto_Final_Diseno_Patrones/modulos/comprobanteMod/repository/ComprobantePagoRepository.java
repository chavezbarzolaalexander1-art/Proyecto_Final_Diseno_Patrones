package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.ComprobantePago;

/**
 * Repositorio JPA para la gestión de persistencia de {@link ComprobantePago}.
 */
@Repository
public interface ComprobantePagoRepository extends JpaRepository<ComprobantePago, Long> {
  /**
   * Verifica si ya existe un comprobante registrado con la serie y número indicados.
   *
   * @param serie serie del comprobante
   * @param numero número correlativo del comprobante
   * @return {@code true} si existe un comprobante con esa serie y número.
   */
  boolean existsBySerieAndNumero(String serie, String numero);

}
