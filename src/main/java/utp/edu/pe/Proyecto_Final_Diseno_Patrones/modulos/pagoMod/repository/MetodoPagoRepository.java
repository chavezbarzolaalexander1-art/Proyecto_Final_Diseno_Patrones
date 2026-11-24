package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model.MetodoPago;


/**
 * Repositorio JPA para la gestión de persistencia de {@link MetodoPago}.
 */
@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Long> {

  /**
   * Busca un método de pago por su nombre exacto.
   *
   * @param nombre nombre del método de pago
   * @return un {@link Optional} con el método de pago si existe, o vacío en caso contrario
   */
  Optional<MetodoPago> findByNombre(String nombre);
}
