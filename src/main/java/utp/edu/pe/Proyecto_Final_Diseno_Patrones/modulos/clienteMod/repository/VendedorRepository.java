package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model.Vendedor;
@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
}
