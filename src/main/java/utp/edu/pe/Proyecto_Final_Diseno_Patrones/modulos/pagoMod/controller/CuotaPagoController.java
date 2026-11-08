package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.ComprobantePago;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.dto.CuotaPagoResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.service.CuotaPagoService;

import java.util.List;

@RestController
@RequestMapping("/api/cuotas-pago")
@RequiredArgsConstructor
public class CuotaPagoController {

    private final CuotaPagoService cuotaPagoService;

    @GetMapping("/comprobante/{comprobanteId}")
    public ResponseEntity<List<CuotaPagoResponse>> listarPorComprobante(@PathVariable Long comprobanteId) {
        return ResponseEntity.ok(cuotaPagoService.listarPorComprobante(comprobanteId));
    }

    @PatchMapping("/{cuotaId}/pagar")
    public ResponseEntity<Void> marcarComoPagada(
            @PathVariable Long cuotaId,
            @RequestParam String metodoPago,
            @RequestParam(required = false) String numeroTransaccion
    ) {
        cuotaPagoService.marcarCuotaComoPagada(cuotaId, metodoPago, numeroTransaccion);
        return ResponseEntity.noContent().build();
    }

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