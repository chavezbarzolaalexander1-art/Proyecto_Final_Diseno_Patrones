package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.reporteComprobanteMod.dto;

import java.util.Arrays;
import java.util.Objects;

/**
 * DTO inmutable que encapsula el archivo de reporte generado junto con sus metadatos
 * necesarios para la descarga o visualización HTTP.
 *
 * @param nombreArchivo nombre sugerido para el archivo generado (incluyendo su extensión)
 * @param contentType tipo MIME del contenido
 * @param contenido arreglo de bytes que representa el contenido binario del reporte
 */
public record ArchivoReporte(
    String nombreArchivo,
    String contentType,
    byte[] contenido
) {
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ArchivoReporte(String nombre, String tipo, byte[] bytes))) {
      return false;
    }
    return Objects.equals(nombreArchivo, nombre)
        && Objects.equals(contentType, tipo)
        && Arrays.equals(contenido, bytes);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(nombreArchivo, contentType);
    result = 31 * result + Arrays.hashCode(contenido);
    return result;
  }

  @Override
  public String toString() {
    return "ArchivoReporte{"
        +
        "nombreArchivo='"
        + nombreArchivo + '\''
        +
        ", contentType='"
        + contentType
        +
        '\''
        +
        ", contenido=" + Arrays.toString(contenido)
        +
        '}';
  }
}