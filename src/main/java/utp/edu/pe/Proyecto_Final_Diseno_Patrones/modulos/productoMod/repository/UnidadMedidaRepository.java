package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.UnidadMedida;

import java.util.Optional;
@Repository
public interface UnidadMedidaRepository extends JpaRepository<UnidadMedida, Long> {
    // Buscar por el código de medida SUNAT (Ej. "NIU", "ZZ", "KG")
    Optional<UnidadMedida> findByCodigo(String codigo);
}
