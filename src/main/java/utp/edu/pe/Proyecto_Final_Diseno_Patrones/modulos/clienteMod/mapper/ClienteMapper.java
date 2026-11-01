package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.ClienteRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.ClienteResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.DireccionDto;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model.Cliente;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model.DireccionCliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteResponse toResponse(Cliente cliente);

    @Mapping(target = "id", ignore = true)
    Cliente toEntity(ClienteRequest request);

    DireccionDto toDireccionDto(DireccionCliente direccion);

    DireccionCliente toDireccionCliente(DireccionDto dto);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromRequest(ClienteRequest request, @MappingTarget Cliente cliente);
}
