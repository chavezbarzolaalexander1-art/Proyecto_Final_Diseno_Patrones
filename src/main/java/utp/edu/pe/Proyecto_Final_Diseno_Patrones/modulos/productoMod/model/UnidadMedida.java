package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad JPA que representa una unidad de medida estándar aplicable a los productos
 * comercializados en el sistema (por ejemplo, NIU, KGM).
 */
@Entity
@Table(name = "unidad_medida")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnidadMedida {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 10)
  private String codigo; // NIU (Unidades), ZZ (Servicios), KG, etc.

  @Column(nullable = false, length = 50)
  private String descripcion;
}