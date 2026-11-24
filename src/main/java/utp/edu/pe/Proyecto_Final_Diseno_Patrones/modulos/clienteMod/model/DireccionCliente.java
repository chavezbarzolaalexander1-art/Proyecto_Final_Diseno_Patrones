package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa la dirección asociada a un cliente.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DireccionCliente {

  @Column(nullable = false, length = 200)
  private String direccion;

  @Column(length = 50)
  private String departamento;

  @Column(length = 50)
  private String provincia;

  @Column(length = 50)
  private String distrito;

}