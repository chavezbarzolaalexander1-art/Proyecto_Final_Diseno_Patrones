package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.service;

import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.ClienteDto;
import java.util.List;
import java.util.Optional;

public interface ClienteService {
    List<ClienteDto> listarClientes();
    Optional<ClienteDto> buscarPorId(Long id);
    ClienteDto guardarCliente(ClienteDto clienteDto);
    void eliminarCliente(Long id);
}
