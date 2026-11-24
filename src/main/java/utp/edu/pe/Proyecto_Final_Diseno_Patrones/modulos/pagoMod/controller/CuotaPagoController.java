package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.dto.CuotaPagoResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.service.CuotaPagoService;

/**
 * Controlador REST para la gestión de cuotas de pago y cronogramas de comprobantes.
 */
@RestController
@RequestMapping("/api/cuotas-pago")
@RequiredArgsConstructor
public class CuotaPagoController {

  private final CuotaPagoService cuotaPagoService;

  /**
   * Lista las cuotas de pago asociadas a un comprobante específico.
   *
   * @param comprobanteId identificador único del comprobante de pago
   * @return respuesta HTTP 200 con la lista de cuotas del comprobante
   */
  @GetMapping("/comprobante/{comprobanteId}")
  public ResponseEntity<List<CuotaPagoResponse>> listarPorComprobante(
      @PathVariable Long comprobanteId) {
    return ResponseEntity.ok(cuotaPagoService.listarPorComprobante(comprobanteId));
  }

  /**
   * Marca una cuota de pago como pagada registrando el método y número de transacción.
   *
   * @param cuotaId identificador único de la cuota a pagar
   * @param metodoPago método utilizado para realizar el pago
   * @param numeroTransaccion número de operación o transacción opcional
   * @return respuesta HTTP 204 sin contenido tras registrar el pago
   */
  @PatchMapping("/{cuotaId}/pagar")
  public ResponseEntity<Void> marcarComoPagada(
      @PathVariable Long cuotaId,
      @RequestParam String metodoPago,
      @RequestParam(required = false) String numeroTransaccion
  ) {
    cuotaPagoService.marcarCuotaComoPagada(cuotaId, metodoPago, numeroTransaccion);
    return ResponseEntity.noContent().build();
  }

  /**
   * Genera el cronograma de cuotas de pago para un comprobante.
   *
   * @param comprobanteId identificador único del comprobante de pago
   * @param numeroCuotas cantidad total de cuotas a generar
   * @param diasEntreCuotas intervalo de días entre cada cuota
   * @return respuesta HTTP 201 tras crear el cronograma de cuotas
   */
  @PostMapping("/comprobante/{comprobanteId}/generar")
  public ResponseEntity<Void> generarCronograma(
      @PathVariable Long comprobanteId,
      @RequestParam Integer numeroCuotas,
      @RequestParam(defaultValue = "30") Integer diasEntreCuotas
  ) {
    cuotaPagoService.generarCronogramaCuotas(comprobanteId, numeroCuotas, diasEntreCuotas);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}