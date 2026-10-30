package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.service;

import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.dto.PagoResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.dto.RegistrarPagoRequest;

import java.util.List;

public interface PagoService {

    PagoResponse registrarPago(RegistrarPagoRequest request);

    List<PagoResponse> listarPorComprobante(Long comprobanteId);

    Double calcularTotalPagado(Long comprobanteId);
}
