package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Documento de Edentidad.
 */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentoIdentidad {

  @Enumerated(EnumType.STRING)
  @Column(name = "tipo_documento", nullable = false, length = 20)
  private TipoDocumento tipoDocumento;

  @Column(name = "numero_documento", nullable = false, unique = true, length = 20)
  private String numeroDocumento;

  public void validar() {
    if (tipoDocumento == null || numeroDocumento == null || numeroDocumento.isBlank()) {
      throw new IllegalArgumentException("El tipo y número de documento son obligatorios.");
    }
    this.numeroDocumento = numeroDocumento.trim().toUpperCase();
    tipoDocumento.validar(this.numeroDocumento);
  }
}
