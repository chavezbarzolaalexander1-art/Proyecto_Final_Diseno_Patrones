package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model.CondicionPago;

/**
 * Repositorio JPA para la gestión de persistencia de {@link CondicionPago}.
 */
@Repository
public interface CondicionPagoRepository extends JpaRepository<CondicionPago, Long> {
  /**
   * Busca una condición de pago por su descripción exacta.
   *
   * @param descripcion descripción de la condición de pago
   * @return un {@link Optional} con la condición de pago si existe, o vacío en caso contrario
   */
  Optional<CondicionPago> findByDescripcion(String descripcion);
}
