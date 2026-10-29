package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.service;

import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.dto.ComprobanteResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.dto.CrearComprobanteRequest;

import java.util.List;

public interface ComprobanteService {
    ComprobanteResponse emitir(CrearComprobanteRequest request);

    List<ComprobanteResponse> listarTodos();

    ComprobanteResponse buscarPorId(Long id);

    ComprobanteResponse buscarPorSerieYNumero(String serie, String numero);

    ComprobanteResponse anular(Long id, String motivo);
}
