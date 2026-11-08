package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.ComprobantePago;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.repository.ComprobantePagoRepository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.dto.CuotaPagoResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.dto.RegistrarPagoRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model.CuotaPago;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.repository.CuotaPagoRepository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.service.CuotaPagoService;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.service.PagoService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CuotaPagoServiceImpl implements CuotaPagoService {
    private final ComprobantePagoRepository comprobantePagoRepository;
    private final CuotaPagoRepository cuotaPagoRepository;
    private final PagoService pagoService;

    @Override
    @Transactional
    public void generarCronogramaCuotas(Long comprobanteId, Integer numeroCuotas, Integer diasEntreCuotas) {
        if (numeroCuotas == null || numeroCuotas <= 1) {
            return;
        }

        // Se busca el comprobante dentro del servicio usando el ID
        ComprobantePago comprobante = comprobantePagoRepository.findById(comprobanteId)
                .orElseThrow(() -> new RuntimeException("No se encontró el comprobante con ID: " + comprobanteId));

        int dias = (diasEntreCuotas != null && diasEntreCuotas > 0) ? diasEntreCuotas : 30;
        BigDecimal montoCuota = comprobante.getTotal()
                .divide(BigDecimal.valueOf(numeroCuotas), 2, RoundingMode.HALF_UP);

        for (int i = 1; i <= numeroCuotas; i++) {
            CuotaPago cuota = CuotaPago.builder()
                    .numeroCuota(i)
                    .monto(montoCuota.doubleValue())
                    .fechaVencimiento(LocalDate.now().plusDays((long) i * dias))
                    .pagado(false)
                    .comprobantePago(comprobante)
                    .build();
            cuotaPagoRepository.save(cuota);
        }
    }
    @Override
    @Transactional(readOnly = true)
    public List<CuotaPagoResponse> listarPorComprobante(Long comprobanteId) {
        return cuotaPagoRepository.findByComprobantePagoIdOrderByNumeroCuotaAsc(comprobanteId).stream()
                .map(c -> new CuotaPagoResponse(
                        c.getId(),
                        c.getNumeroCuota(),
                        c.getMonto(),
                        c.getFechaVencimiento(),
                        c.getPagado()
                ))
                .toList();
    }
    @Override
    @Transactional
    public void marcarCuotaComoPagada(Long cuotaId, String metodoPago, String numeroTransaccion) {
        CuotaPago cuota = cuotaPagoRepository.findById(cuotaId)
                .orElseThrow(() -> new RuntimeException("No se encontró la cuota con ID: " + cuotaId));

        if (Boolean.TRUE.equals(cuota.getPagado())) {
            throw new IllegalArgumentException("La cuota ya se encuentra pagada.");
        }

        // 1. Marcar estado de cuota en el cronograma
        cuota.setPagado(true);
        cuotaPagoRepository.save(cuota);

        // 2. Registrar la entrada de dinero en el módulo de pagos (dispara el patrón State)
        RegistrarPagoRequest pagoRequest = new RegistrarPagoRequest(
                cuota.getComprobantePago().getId(),
                metodoPago,
                cuota.getMonto(),
                numeroTransaccion
        );

        pagoService.registrarPago(pagoRequest);
    }
}
