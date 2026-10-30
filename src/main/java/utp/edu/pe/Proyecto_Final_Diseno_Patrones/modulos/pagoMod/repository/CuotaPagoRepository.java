package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model.CuotaPago;

import java.util.List;

@Repository
public interface CuotaPagoRepository extends JpaRepository<CuotaPago, Long> {

    List<CuotaPago> findByComprobantePagoIdOrderByNumeroCuotaAsc(Long comprobanteId);

    List<CuotaPago> findByComprobantePagoIdAndPagadoFalse(Long comprobanteId);
}
