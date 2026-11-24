package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model.CondicionPago;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.service.CondicionPagoService;

/**
 * Controlador REST para la gestión de condiciones de pago.
 */
@RestController
@RequestMapping("/api/condiciones-pago")
@RequiredArgsConstructor
public class CondicionPagoController {

  private final CondicionPagoService condicionPagoService;

  /**
   * Lista todas las condiciones de pago registradas.
   *
   * @return respuesta HTTP 200 con la lista de condiciones de pago
   */
  @GetMapping
  public ResponseEntity<List<CondicionPago>> listarTodas() {
    return ResponseEntity.ok(condicionPagoService.listarTodas());
  }

  /**
   * Obtiene una condición de pago existente por su descripción o la crea si no existe.
   *
   * @param descripcion descripción de la condición de pago
   * @return respuesta HTTP 200 con la condición de pago encontrada o creada
   */
  @PostMapping
  public ResponseEntity<CondicionPago> obtenerCrear(@RequestParam String descripcion) {
    return ResponseEntity.ok(condicionPagoService.buscarOcrear(descripcion));
  }
}