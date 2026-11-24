package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.dto.ComprobanteResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.dto.CrearComprobanteRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.service.ComprobanteService;


/**
 * Controlador REST para la gestión y emisión de comprobantes de pago.
 */
@RestController
@RequestMapping("/api/comprobantes")
@RequiredArgsConstructor
@Validated
public class ComprobanteController {
  private final ComprobanteService comprobanteService;

  /**
   * Emite un nuevo comprobante de pago.
   *
   * @param request datos necesarios para emitir el comprobante
   * @return respuesta HTTP 201 con el comprobante creado
   */
  @PostMapping
  public ResponseEntity<ComprobanteResponse> emitir(
      @Valid @RequestBody CrearComprobanteRequest request
  ) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(comprobanteService.emitir(request));
  }

  /**
   * Lista todos los comprobantes de pago registrados.
   *
   * @return respuesta HTTP 200 con la lista de comprobantes
   */
  @GetMapping
  public ResponseEntity<List<ComprobanteResponse>> listarTodos() {
    return ResponseEntity.ok(comprobanteService.listarTodos());
  }

  /**
   * Busca un comprobante de pago por su identificador único.
   *
   * @param id identificador del comprobante
   * @return respuesta HTTP 200 con el comprobante encontrado
   */
  @GetMapping("/{id}")
  public ResponseEntity<ComprobanteResponse> buscarPorId(@PathVariable Long id) {
    return ResponseEntity.ok(comprobanteService.buscarPorId(id));
  }
}
