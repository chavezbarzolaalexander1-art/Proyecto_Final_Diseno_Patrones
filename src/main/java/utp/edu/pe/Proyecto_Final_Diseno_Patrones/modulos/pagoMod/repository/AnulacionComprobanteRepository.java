package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model.AnulacionComprobante;

/**
 * Repositorio para gestionar las operaciones de persistencia de {@link AnulacionComprobante}.
 */
public interface AnulacionComprobanteRepository extends JpaRepository<AnulacionComprobante, Long> {
}
