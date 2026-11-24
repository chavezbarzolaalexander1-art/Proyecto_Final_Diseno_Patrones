package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model.CuotaPago;

/**
 * Repositorio JPA para la gestión de persistencia de {@link CuotaPago}.
 */
@Repository
public interface CuotaPagoRepository extends JpaRepository<CuotaPago, Long> {
  /**
   * Obtiene la lista de cuotas de pago asociadas a un comprobante,
   * ordenadas ascendentemente por número de cuota.
   *
   * @param comprobanteId identificador único del comprobante de pago
   * @return lista de cuotas ordenadas por su número correlativo
   */
  List<CuotaPago> findByComprobantePagoIdOrderByNumeroCuotaAsc(Long comprobanteId);

}
