package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.ClienteRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.ClienteResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.mapper.ClienteMapper;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model.Cliente;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.repository.ClienteRepository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.service.ClienteService;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Override
    public ClienteResponse guardarCliente(ClienteRequest request) {
        Cliente cliente = clienteMapper.toEntity(request);
        Cliente clienteGuardado = clienteRepository.save(cliente);
        return clienteMapper.toResponse(clienteGuardado);
    }

    @Override
    public ClienteResponse actualizarCliente(Long id, ClienteRequest request) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));
        clienteMapper.updateEntityFromRequest(request, clienteExistente);


        return clienteMapper.toResponse(clienteRepository.save(clienteExistente));
    }

    @Override
    public List<ClienteResponse> listarClientes() {
        return clienteRepository.findAll().stream().map(clienteMapper::toResponse).toList();
    }

    @Override
    public ClienteResponse buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));
        return clienteMapper.toResponse(cliente);
    }

    @Override
    public void eliminarCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente no encontrado con id: " + id);
        }
        clienteRepository.deleteById(id);
    }
}
