package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.service;

import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.VendedorRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.VendedorResponse;

import java.util.List;

public interface VendedorService {
    List<VendedorResponse> listarVendedores();
    VendedorResponse buscarPorId(Long id);
    VendedorResponse guardarVendedor(VendedorRequest request);
    VendedorResponse actualizarVendedor(Long id, VendedorRequest request);
    void eliminarVendedor (Long id);
}
