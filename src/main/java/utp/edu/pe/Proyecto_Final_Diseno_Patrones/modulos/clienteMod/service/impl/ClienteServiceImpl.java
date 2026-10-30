package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.service.impl;

import org.springframework.stereotype.Service;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.ClienteDto;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.service.ClienteService;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Override
    public List<ClienteDto> listarClientes() {
        return List.of();
    }

    @Override
    public Optional<ClienteDto> buscarPorId(Long id) {
        return Optional.empty();
    }

    @Override
    public ClienteDto guardarCliente(ClienteDto clienteDto) {
        return clienteDto;
    }

    @Override
    public void eliminarCliente(Long id) {
    }
}