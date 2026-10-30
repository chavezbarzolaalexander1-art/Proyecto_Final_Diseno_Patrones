package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.dto.PagoResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.dto.RegistrarPagoRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.service.PagoService;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
@Validated
public class PagoController {

    private final PagoService pagoService;

    /**
     * Registra un pago sobre un comprobante. Medios soportados: EFECTIVO, TARJETA,
     * TRANSFERENCIA y YAPE/PLIN (patrón Strategy).
     * @param request Datos del pago.
     * @return El pago registrado con su comisión y monto neto, HTTP 201 Created.
     */
    @PostMapping
    public ResponseEntity<PagoResponse> registrarPago(@Valid @RequestBody RegistrarPagoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pagoService.registrarPago(request));
    }

    /**
     * Lista los pagos realizados para un comprobante.
     * @param comprobanteId Identificador del comprobante.
     * @return Lista de pagos con estado HTTP 200 OK.
     */
    @GetMapping("/comprobante/{comprobanteId}")
    public ResponseEntity<List<PagoResponse>> listarPorComprobante(@PathVariable Long comprobanteId) {
        return ResponseEntity.ok(pagoService.listarPorComprobante(comprobanteId));
    }

    /**
     * Calcula el total pagado de un comprobante.
     * @param comprobanteId Identificador del comprobante.
     * @return Suma de los montos pagados con estado HTTP 200 OK.
     */
    @GetMapping("/comprobante/{comprobanteId}/total")
    public ResponseEntity<Double> calcularTotalPagado(@PathVariable Long comprobanteId) {
        return ResponseEntity.ok(pagoService.calcularTotalPagado(comprobanteId));
    }
}
