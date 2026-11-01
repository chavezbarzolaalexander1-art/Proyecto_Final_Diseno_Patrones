package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.service;

import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.EmpresaRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.EmpresaResponse;

import java.util.List;

public interface EmpresaService {
    List<EmpresaResponse> listarEmpresas();
    EmpresaResponse buscarPorId(Long id);
    EmpresaResponse guardarEmpresa(EmpresaRequest request);
    EmpresaResponse actualizarEmpresa(Long id, EmpresaRequest request);
    void eliminarEmpresa(Long id);

}
