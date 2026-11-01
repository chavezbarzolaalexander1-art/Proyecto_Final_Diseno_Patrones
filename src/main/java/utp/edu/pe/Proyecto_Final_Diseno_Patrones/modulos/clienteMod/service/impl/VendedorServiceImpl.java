package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.VendedorRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.VendedorResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.mapper.VendedorMapper;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model.Vendedor;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.repository.VendedorRepository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.service.VendedorService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class VendedorServiceImpl implements VendedorService {
    private final VendedorRepository vendedorRepository;
    private final VendedorMapper vendedorMapper;

    @Override
    public List<VendedorResponse> listarVendedores() {
        return vendedorRepository.findAll()
                .stream()
                .map(vendedorMapper::toResponse)
                .toList();
    }

    @Override
    public VendedorResponse buscarPorId(Long id) {
        Vendedor v = vendedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado con id: " + id));
        return vendedorMapper.toResponse(v);
    }

    @Override
    public VendedorResponse guardarVendedor(VendedorRequest request) {
        Vendedor v = vendedorMapper.toEntity(request);
        return vendedorMapper.toResponse(vendedorRepository.save(v));
    }

    @Override
    public VendedorResponse actualizarVendedor(Long id, VendedorRequest request) {
        Vendedor v = vendedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado con id: " + id));

        vendedorMapper.updateEntityFromRequest(request, v);

        return vendedorMapper.toResponse(vendedorRepository.save(v));
    }

    @Override
    public void eliminarVendedor(Long id) {
        if (!vendedorRepository.existsById(id)) {
            throw new RuntimeException("Vendedor no encontrado con id: " + id);
        }
        vendedorRepository.deleteById(id);
    }
}
