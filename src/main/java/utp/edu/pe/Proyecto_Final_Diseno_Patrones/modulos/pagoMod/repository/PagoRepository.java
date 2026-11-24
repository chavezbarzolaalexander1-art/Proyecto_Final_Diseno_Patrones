package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model.Pago;

/**
 * Repositorio JPA para la gestión de persistencia de {@link Pago}.
 */
@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
  /**
   * Obtiene la lista de pagos asociados a un comprobante de pago específico.
   *
   * @param comprobanteId identificador único del comprobante de pago
   * @return lista de pagos registrados para el comprobante indicado
   */
  List<Pago> findByComprobantePagoId(Long comprobanteId);
}
