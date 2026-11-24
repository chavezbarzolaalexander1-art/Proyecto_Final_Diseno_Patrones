package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.ComprobantePago;



/**
 * Entidad que representa la anulación de un comprobante de pago.
 */
@Entity
@Table(name = "anulacion_comprobante")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnulacionComprobante {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 255)
  private String motivo;

  @Column(nullable = false)
  private LocalDateTime fechaAnulacion;

  @OneToOne
  @JoinColumn(name = "comprobante_id", nullable = false, unique = true)
  private ComprobantePago comprobantePago;
}