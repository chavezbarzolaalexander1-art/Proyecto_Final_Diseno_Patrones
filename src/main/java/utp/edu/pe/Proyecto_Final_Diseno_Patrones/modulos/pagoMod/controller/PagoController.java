package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.dto.PagoResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.dto.RegistrarPagoRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.service.PagoService;

/**
 * Controlador REST para el registro y consulta de pagos asociados a comprobantes.
 */
@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
@Validated
public class PagoController {
  private final PagoService pagoService;

  /**
   * Registra un nuevo pago asociado a un comprobante.
   *
   * @param request datos necesarios para registrar el pago
   * @return respuesta HTTP 201 con el pago registrado
   */
  @PostMapping
  public ResponseEntity<PagoResponse> registrarPago(
      @Valid @RequestBody RegistrarPagoRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(pagoService.registrarPago(request));
  }

  /**
   * Lista todos los pagos registrados para un comprobante específico.
   *
   * @param comprobanteId identificador único del comprobante de pago
   * @return respuesta HTTP 200 con la lista de pagos del comprobante
   */
  @GetMapping("/comprobante/{comprobanteId}")
  public ResponseEntity<List<PagoResponse>> listarPorComprobante(@PathVariable Long comprobanteId) {
    return ResponseEntity.ok(pagoService.listarPorComprobante(comprobanteId));
  }

  /**
   * Calcula el monto total pagado acumulado para un comprobante específico.
   *
   * @param comprobanteId identificador único del comprobante de pago
   * @return respuesta HTTP 200 con el importe total pagado
   */
  @GetMapping("/comprobante/{comprobanteId}/total")
  public ResponseEntity<Double> calcularTotalPagado(@PathVariable Long comprobanteId) {
    return ResponseEntity.ok(pagoService.calcularTotalPagado(comprobanteId));
  }
}
