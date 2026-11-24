package utp.edu.pe.Proyecto_Final_Diseno_Patrones.config;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Manejador global de excepciones para centralizar y estructurar las respuestas de error
 * de la API REST.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
  private static final ZoneId ZONA_LIMA = ZoneId.of("America/Lima");

  /**
   *Maneja las excepciones de argumentos inválidos o reglas de negocio incumplidas.
   *
   * @param ex excepción capturada con el detalle del error de validación o argumento
   * @return respuesta HTTP 400 (Bad Request) con el detalle del error
   */
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<Map<String, Object>> manejarIllegalArgument(IllegalArgumentException ex) {
    return construirRespuesta(HttpStatus.BAD_REQUEST, "Solicitud Incorrecta", ex.getMessage());
  }

  /**
   *Maneja las excepciones cuando un recurso solicitado no existe en el sistema.
   *
   * @param ex excepción capturada indicando que el elemento no fue encontrado
   * @return respuesta HTTP 404 (Not Found) con el detalle del error
   */
  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<Map<String, Object>> manejarNoEncontrado(NoSuchElementException ex) {
    return construirRespuesta(HttpStatus.NOT_FOUND, "Recurso No Encontrado", ex.getMessage());
  }

  /**
   * Maneja cualquier excepción no controlada de forma general en el servidor.
   *
   * @param ex excepción general capturada
   * @return respuesta HTTP 500 (Internal Server Error) con el detalle del error
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, Object>> manejarExceptionGeneral(Exception ex) {
    return construirRespuesta(HttpStatus.INTERNAL_SERVER_ERROR, "Error Interno del Servidor",
        ex.getMessage());
  }

  private ResponseEntity<Map<String, Object>> construirRespuesta(HttpStatus status, String error,
                                                                 String mensaje) {
    Map<String, Object> respuesta = new HashMap<>();
    respuesta.put("timestamp", LocalDateTime.now(ZONA_LIMA));
    respuesta.put("status", status.value());
    respuesta.put("error", error);
    respuesta.put("message", mensaje);
    return new ResponseEntity<>(respuesta, status);
  }
}
