package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.*;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.Producto;

/**
 * Entidad que representa un ítem o línea de detalle dentro de un comprobante de pago.
 */
@Entity
@Table(name = "detalle_comprobante")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleComprobante {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Integer cantidad;

  @Column(nullable = false, precision = 12, scale = 2)
  private BigDecimal precioUnitario;

  @Column(nullable = false, precision = 12, scale = 2)
  private BigDecimal subtotal;

  @ManyToOne
  @JoinColumn(name = "comprobante_id", nullable = false)
  @JsonIgnore
  private ComprobantePago comprobantePago;

  @ManyToOne
  @JoinColumn(name = "producto_id", nullable = false)
  private Producto producto;
}
