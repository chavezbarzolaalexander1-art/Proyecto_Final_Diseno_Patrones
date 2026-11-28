package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model;

/**
 * Define los tipos de documentos de identificación permitidos.
 */
public enum TipoDocumento {
  DNI("\\d{8}", "El DNI debe tener exactamente 8 dígitos."),
  RUC("(10|20)\\d{9}", "El RUC debe tener 11 dígitos e iniciar con 10 o 20."),
  CE("[A-Z0-9]{6,20}", "El Carnet de Extranjería debe "
      + "tener entre 6 y 20 caracteres alfanuméricos."),
  PASAPORTE("[A-Z0-9]{6,20}", "El Pasaporte debe tener entre 6 y 20 caracteres alfanuméricos.");

  private final String patron;
  private final String mensajeError;

  TipoDocumento(String patron, String mensajeError) {
    this.patron = patron;
    this.mensajeError = mensajeError;
  }

  /**
   * Verifica que el número de documento cumpla con el formato establecido para este tipo.
   *
   * @param numero Cadena con el número de documento a validar.
   * @throws IllegalArgumentException Si el número es nulo o no coincide con la expresión regular.
   */
  public void validar(String numero) {
    if (numero == null || !numero.matches(patron)) {
      throw new IllegalArgumentException(mensajeError);
    }
  }
}
