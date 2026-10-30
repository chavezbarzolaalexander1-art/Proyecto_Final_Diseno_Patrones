package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model.Pago;

import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {

    List<Pago> findByComprobantePagoId(Long comprobanteId);
}
