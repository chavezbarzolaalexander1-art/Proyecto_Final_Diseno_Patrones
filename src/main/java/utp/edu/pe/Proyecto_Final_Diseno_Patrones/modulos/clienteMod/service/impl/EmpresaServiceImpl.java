package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.EmpresaRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.EmpresaResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.mapper.EmpresaMapper;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model.Empresa;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.repository.EmpresaRepository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.service.EmpresaService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpresaServiceImpl implements EmpresaService {
    private final EmpresaRepository empresaRepository;
    private final EmpresaMapper empresaMapper;

    @Override
    public List<EmpresaResponse> listarEmpresas() {
        return empresaRepository.findAll()
                .stream()
                .map(empresaMapper::toResponse)
                .toList();
    }

    @Override
    public EmpresaResponse buscarPorId(Long id) {
        Empresa e = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada con id: " + id));
        return empresaMapper.toResponse(e);
    }

    @Override
    public EmpresaResponse guardarEmpresa(EmpresaRequest request) {
        Empresa e = empresaMapper.toEntity(request);
        return empresaMapper.toResponse(empresaRepository.save(e));
    }

    @Override
    public EmpresaResponse actualizarEmpresa(Long id, EmpresaRequest request) {
        Empresa e = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada con id: " + id));

        empresaMapper.updateEntityFromRequest(request, e);

        return empresaMapper.toResponse(empresaRepository.save(e));
    }

    @Override
    public void eliminarEmpresa(Long id) {
        if (!empresaRepository.existsById(id)) {
            throw new RuntimeException("Empresa no encontrada con id: " + id);
        }
        empresaRepository.deleteById(id);
    }
}
