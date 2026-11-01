package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.EmpresaRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.EmpresaResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model.Empresa;

@Mapper(componentModel = "spring")
public interface EmpresaMapper {
    EmpresaResponse toResponse(Empresa empresa);

    @Mapping(target = "id", ignore = true)
    Empresa toEntity(EmpresaRequest request);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromRequest(EmpresaRequest request, @MappingTarget Empresa empresa);
}
