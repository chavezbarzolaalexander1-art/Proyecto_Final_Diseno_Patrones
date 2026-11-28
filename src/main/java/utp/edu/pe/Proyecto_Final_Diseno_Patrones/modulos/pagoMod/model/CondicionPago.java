package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * Entidad que representa una condición de pago
 * aplicable a un comprobante (por ejemplo, contado o crédito).
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
  private String descripcion; // CONTADO o CRÉDITO.

  /**
   * Metodo para validar la coadicon del pago.
   *
   * @param montoPago moto a pagar.
   * @param totalComprobante total a pagar.
   */
  public void validarPago(Double montoPago, BigDecimal totalComprobante) {
    if (descripcion != null && "CONTADO".equalsIgnoreCase(descripcion.trim())) {
      BigDecimal monto = BigDecimal.valueOf(montoPago).setScale(2, RoundingMode.HALF_UP);
      if (monto.compareTo(totalComprobante) != 0) {
        throw new IllegalArgumentException(
            "Un comprobante al CONTADO requiere el pago completo de S/ " + totalComprobante + "."
        );
      }
    }
  }
}