package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.ComprobantePago;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComprobantePagoRepository extends JpaRepository<ComprobantePago, Long> {
    boolean existsBySerieAndNumero(String serie, String numero);

    @EntityGraph(attributePaths = {
            "cliente", "vendedor", "estado", "detalles", "detalles.producto"
    })
    Optional<ComprobantePago> findBySerieAndNumero(String serie, String numero);

    @Override
    @EntityGraph(attributePaths = {
            "cliente", "vendedor", "estado", "detalles", "detalles.producto"
    })
    Optional<ComprobantePago> findById(Long id);

    @Override
    @EntityGraph(attributePaths = {
            "cliente", "vendedor", "estado", "detalles", "detalles.producto"
    })
    List<ComprobantePago> findAll();
}
