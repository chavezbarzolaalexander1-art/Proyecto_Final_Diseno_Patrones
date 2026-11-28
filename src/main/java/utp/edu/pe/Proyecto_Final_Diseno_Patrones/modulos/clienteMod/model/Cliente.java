package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que representa un cliente en la base de datos.
 */
@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 150)
  private String razonSocialONombre;

  @Embedded
  private DocumentoIdentidad documentoIdentidad;

  @Embedded
  private DatosContacto datosContacto;

  @Embedded
  private DireccionCliente direccion;
}