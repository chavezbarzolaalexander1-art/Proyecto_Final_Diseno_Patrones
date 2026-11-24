package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que representa un método de pago disponible para registrar transacciones.
 */
@Entity
@Table(name = "metodo_pago")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MetodoPago {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 50)
  private String nombre; // EFECTIVO, TARJETA, TRANSFERENCIA, YAPE/PLIN
}