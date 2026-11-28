package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que recibe los Datos de Contacto.
 */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DatosContacto {

  @Column(name = "email", length = 100)
  private String email;

  @Column(name = "telefono", length = 15)
  private String telefono;

  public void validar() {
    if (email != null && !email.isBlank()) {
      this.email = email.trim().toLowerCase();
      if (!this.email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
        throw new IllegalArgumentException("El formato del correo electrónico no es válido.");
      }
    }
    if (telefono != null && !telefono.isBlank()) {
      this.telefono = telefono.trim();
      if (!this.telefono.matches("\\d{7,15}")) {
        throw new IllegalArgumentException("El teléfono debe contener entre 7 y 15 dígitos.");
      }
    }
  }
}