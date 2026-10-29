package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.EstadoComprobante;

import java.util.Optional;

@Repository
public interface EstadoComprobanteRepository extends JpaRepository<EstadoComprobante, Long> {
    Optional<EstadoComprobante> findByNombreIgnoreCase(String nombre);
}
