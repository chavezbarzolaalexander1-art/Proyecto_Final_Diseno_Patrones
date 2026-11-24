package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad JPA que representa un impuesto aplicable a los productos registrados en el sistema.
 */
@Entity
@Table(name = "impuesto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Impuesto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 50)
  private String nombre; // IGV, Exonerado, Inafecto

  @Column(nullable = false)
  private Double porcentaje; // Ej. 18.00
}