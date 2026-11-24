package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.dto.AnulacionRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.service.AnulacionComprobanteService;

/**
 * Controlador REST para gestionar las anulaciones de comprobantes de pago.
 */
@RestController
@RequestMapping("/api/anulaciones")
@RequiredArgsConstructor
@Validated
public class AnulacionComprobanteController {

  private final AnulacionComprobanteService anulacionComprobanteService;

  /**
   * Anula un comprobante de pago existente a partir de su identificador.
   *
   * @param comprobanteId identificador único del comprobante a anular
   * @param request       datos de la solicitud de anulación
   * @return respuesta HTTP 204 (No Content) si la anulación fue exitosa
   */
  @PostMapping("/comprobante/{comprobanteId}")
  public ResponseEntity<Void> anularComprobante(
      @PathVariable Long comprobanteId,
      @Valid @RequestBody AnulacionRequest request
  ) {
    anulacionComprobanteService.anularComprobante(comprobanteId, request);
    return ResponseEntity.noContent().build();
  }
}