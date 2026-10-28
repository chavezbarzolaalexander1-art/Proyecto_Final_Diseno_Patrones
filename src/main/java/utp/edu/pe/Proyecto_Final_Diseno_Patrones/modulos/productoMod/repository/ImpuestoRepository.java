package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.Impuesto;

import java.util.Optional;
@Repository
public interface ImpuestoRepository extends JpaRepository<Impuesto, Long> {
    // Buscar impuesto por su nombre (Ej. "IGV", "Exonerado")
    Optional<Impuesto> findByNombre(String nombre);
}
