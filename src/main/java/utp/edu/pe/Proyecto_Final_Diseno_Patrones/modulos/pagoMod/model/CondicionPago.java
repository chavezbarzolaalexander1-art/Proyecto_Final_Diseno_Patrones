package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que representa una condición de pago aplicable a un comprobante (por ejemplo, contado o crédito).
 */
@Entity
@Table(name = "condicion_pago")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CondicionPago {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 50)
  private String descripcion; // CONTADO, CRÉDITO 30 DÍAS, CRÉDITO CUOTAS
}