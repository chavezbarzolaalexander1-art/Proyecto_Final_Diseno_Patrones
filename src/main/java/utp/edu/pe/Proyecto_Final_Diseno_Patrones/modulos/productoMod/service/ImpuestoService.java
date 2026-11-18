// ImpuestoService.java
package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service;

import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.ImpuestoRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.ImpuestoResponse;
import java.util.List;

public interface ImpuestoService {
    ImpuestoResponse registrarImpuesto(ImpuestoRequest request);
    List<ImpuestoResponse> listarTodos();
    ImpuestoResponse buscarPorId(Long id);
    ImpuestoResponse actualizarImpuesto(Long id, ImpuestoRequest request);
    void eliminarImpuesto(Long id);
}