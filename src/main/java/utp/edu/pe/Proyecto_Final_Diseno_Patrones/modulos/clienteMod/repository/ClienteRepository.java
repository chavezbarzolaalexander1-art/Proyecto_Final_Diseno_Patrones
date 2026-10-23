package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model.Cliente;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Método opcional para buscar un cliente por su número de documento
    Optional<Cliente> findByNumeroDocumento(String numeroDocumento);
}