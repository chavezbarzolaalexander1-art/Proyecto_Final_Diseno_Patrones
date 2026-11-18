package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.UnidadMedidaRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.UnidadMedidaResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.UnidadMedida;

@Mapper(componentModel = "spring")
public interface UnidadMedidaMapper {
    UnidadMedidaResponse toResponse(UnidadMedida unidadMedida);

    @Mapping(target = "id", ignore = true)
    UnidadMedida toEntity(UnidadMedidaRequest request);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromRequest(UnidadMedidaRequest request, @MappingTarget UnidadMedida unidadMedida);
}