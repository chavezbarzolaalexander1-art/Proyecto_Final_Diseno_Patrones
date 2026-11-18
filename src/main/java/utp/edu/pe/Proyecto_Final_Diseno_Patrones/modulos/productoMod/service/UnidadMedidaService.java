// UnidadMedidaService.java
package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service;

import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.UnidadMedidaRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.UnidadMedidaResponse;
import java.util.List;

public interface UnidadMedidaService {
    UnidadMedidaResponse registrarUnidadMedida(UnidadMedidaRequest request);
    List<UnidadMedidaResponse> listarTodas();
    UnidadMedidaResponse buscarPorId(Long id);
    UnidadMedidaResponse actualizarUnidadMedida(Long id, UnidadMedidaRequest request);
    void eliminarUnidadMedida(Long id);
}