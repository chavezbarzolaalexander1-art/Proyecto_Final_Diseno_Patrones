package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.ComprobantePago;

@Repository
public interface ComprobantePagoRepository extends JpaRepository<ComprobantePago, Long> {
    // Validar duplicidad de serie y correlativo antes de emitir
    boolean existsBySerieAndNumero(String serie, String numero);


}
