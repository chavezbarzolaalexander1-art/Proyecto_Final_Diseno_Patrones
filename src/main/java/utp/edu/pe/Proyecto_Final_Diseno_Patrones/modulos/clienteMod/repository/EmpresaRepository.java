package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model.Empresa;



/**
 * Repositorio para gestionar las operaciones de persistencia de empresas.
 */
@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
  /**
   * Busca una empresa por su RUC.
   *
   * @param ruc RUC de la empresa.
   * @return Empresa encontrada, si existe.
   */
  Optional<Empresa> findByRuc(String ruc);
}
