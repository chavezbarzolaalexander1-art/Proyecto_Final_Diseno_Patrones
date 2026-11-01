package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.ComprobantePago;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.dto.CuotaPagoResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model.CuotaPago;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.repository.CuotaPagoRepository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.service.CuotaPagoService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CuotaPagoServiceImpl implements CuotaPagoService {
    private final CuotaPagoRepository cuotaPagoRepository;

    @Override
    @Transactional
    public void generarCronogramaCuotas(ComprobantePago comprobante, Integer numeroCuotas, Integer diasEntreCuotas) {
        if (numeroCuotas == null || numeroCuotas <= 1) {
            return;
        }

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
    public void marcarCuotaComoPagada(Long cuotaId) {
        CuotaPago cuota = cuotaPagoRepository.findById(cuotaId)
                .orElseThrow(() -> new RuntimeException("No se encontró la cuota con ID: " + cuotaId));
        cuota.setPagado(true);
        cuotaPagoRepository.save(cuota);
    }
}
