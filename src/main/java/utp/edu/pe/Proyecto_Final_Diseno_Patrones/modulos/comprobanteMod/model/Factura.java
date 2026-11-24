package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad que representa una factura electrónica como tipo de comprobante de pago.
 */
@Entity
@Table(name = "factura")
@Getter
@Setter
@NoArgsConstructor
public class Factura extends ComprobantePago {

  @Column(length = 20)
  private String ordenCompra;

  @Column(length = 200)
  private String guiaRemision;

  @Override
  public TipoComprobante getTipoComprobante() {
    return TipoComprobante.FACTURA;
  }

  @Override
  public String getTituloDocumento() {
    return "FACTURA ELECTRÓNICA";
  }

  @Override
  public Map<String, String> getDatosAdicionalesDocumento() {
    Map<String, String> datos = new LinkedHashMap<>();
    if (ordenCompra != null && !ordenCompra.isBlank()) {
      datos.put("Orden de Compra", ordenCompra);
    }
    if (guiaRemision != null && !guiaRemision.isBlank()) {
      datos.put("Guía de Remisión", guiaRemision);
    }
    return datos;
  }
}
