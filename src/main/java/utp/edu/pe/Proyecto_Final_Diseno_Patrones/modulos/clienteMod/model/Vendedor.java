package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que representa un vendedor en la base de datos.
 */
@Entity
@Table(name = "vendedor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vendedor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String nombres;

  @Column(nullable = false, length = 100)
  private String apellidos;

  @Column(nullable = false, unique = true, length = 8)
  private String dni;

  @Column(length = 50)
  private String codigoVendedor;
}