package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model.AnulacionComprobante;

@Repository
public interface AnulacionComprobanteRepository extends JpaRepository<AnulacionComprobante, Long> {
    boolean existsByComprobantePagoId(Long comprobanteId);
}
