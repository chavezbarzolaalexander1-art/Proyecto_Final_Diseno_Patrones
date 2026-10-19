package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model.Empresa;

import java.util.Optional;
@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    //Metodo para buscar una empresa por Ruc
    Optional<Empresa> findByRuc(String ruc);
}
