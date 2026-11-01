package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.service;

import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.ComprobantePago;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.dto.CuotaPagoResponse;

import java.util.List;

public interface CuotaPagoService {
    void generarCronogramaCuotas(ComprobantePago comprobante, Integer numeroCuotas, Integer diasEntreCuotas);

    List<CuotaPagoResponse> listarPorComprobante(Long comprobanteId);

    void marcarCuotaComoPagada(Long cuotaId);
}
