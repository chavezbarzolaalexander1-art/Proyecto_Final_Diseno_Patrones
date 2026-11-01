package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.VendedorRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.VendedorResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model.Vendedor;

@Mapper(componentModel = "spring")
public interface VendedorMapper {

    VendedorResponse toResponse(Vendedor vendedor);

    @Mapping(target = "id", ignore = true)
    Vendedor toEntity(VendedorRequest request);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromRequest(VendedorRequest request, @MappingTarget Vendedor vendedor);
}
