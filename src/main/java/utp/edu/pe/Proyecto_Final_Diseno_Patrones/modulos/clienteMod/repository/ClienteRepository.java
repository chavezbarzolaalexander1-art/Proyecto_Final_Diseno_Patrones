package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model.Cliente;

/**
 * Repositorio para gestionar las operaciones de persistencia de clientes.
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

  /**
   * Busca un cliente por su número de documento.
   *
   * @param numeroDocumento Número de documento del cliente.
   * @return Cliente encontrado, si existe.
   */
  Optional<Cliente> findByNumeroDocumento(String numeroDocumento);
}


