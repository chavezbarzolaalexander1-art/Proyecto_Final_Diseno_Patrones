package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.Categoria;

import java.util.Optional;
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    // Buscar categoría por nombre para evitar duplicados al registrar
    Optional<Categoria> findByNombre(String nombre);
}
