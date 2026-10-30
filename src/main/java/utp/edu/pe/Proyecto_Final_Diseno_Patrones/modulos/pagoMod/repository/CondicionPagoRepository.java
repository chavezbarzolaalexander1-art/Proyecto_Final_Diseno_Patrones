package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model.CondicionPago;

import java.util.Optional;

@Repository
public interface CondicionPagoRepository extends JpaRepository<CondicionPago, Long> {

    Optional<CondicionPago> findByDescripcion(String descripcion);
}
