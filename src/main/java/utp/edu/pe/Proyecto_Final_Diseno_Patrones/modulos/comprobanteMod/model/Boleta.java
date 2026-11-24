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
 * Entidad que representa una boleta de venta como tipo de comprobante de pago.
 */
@Entity
@Table(name = "boleta")
@Getter
@Setter
@NoArgsConstructor
public class Boleta extends ComprobantePago {

  @Column(length = 100)
  private String observaciones;

  @Override
  public TipoComprobante getTipoComprobante() {
    return TipoComprobante.BOLETA;
  }

  @Override
  public String getTituloDocumento() {
    return "BOLETA DE VENTA ELECTRÓNICA";
  }

  @Override
  public Map<String, String> getDatosAdicionalesDocumento() {
    Map<String, String> datos = new LinkedHashMap<>();
    if (observaciones != null && !observaciones.isBlank()) {
      datos.put("Observaciones", observaciones);
    }
    return datos;
  }
}
