package utp.edu.pe.Proyecto_Final_Diseno_Patrones.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final ZoneId ZONA_LIMA = ZoneId.of("America/Lima");

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> manejarIllegalArgument(IllegalArgumentException ex) {
        return construirRespuesta(HttpStatus.BAD_REQUEST, "Solicitud Incorrecta", ex.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, Object>> manejarNoEncontrado(NoSuchElementException ex) {
        return construirRespuesta(HttpStatus.NOT_FOUND, "Recurso No Encontrado", ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> manejarExceptionGeneral(Exception ex) {
        return construirRespuesta(HttpStatus.INTERNAL_SERVER_ERROR, "Error Interno del Servidor", ex.getMessage());
    }

    private ResponseEntity<Map<String, Object>> construirRespuesta(HttpStatus status, String error, String mensaje) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("timestamp", LocalDateTime.now(ZONA_LIMA));
        respuesta.put("status", status.value());
        respuesta.put("error", error);
        respuesta.put("message", mensaje);
        return new ResponseEntity<>(respuesta, status);
    }
}
