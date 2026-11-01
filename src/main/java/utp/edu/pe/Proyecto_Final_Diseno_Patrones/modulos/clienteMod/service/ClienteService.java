package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.service;

import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.ClienteRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.ClienteResponse;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    List<ClienteResponse> listarClientes();
    ClienteResponse buscarPorId(Long id);
    ClienteResponse guardarCliente(ClienteRequest request);
    ClienteResponse actualizarCliente(Long id, ClienteRequest request);
    void eliminarCliente(Long id);
}
