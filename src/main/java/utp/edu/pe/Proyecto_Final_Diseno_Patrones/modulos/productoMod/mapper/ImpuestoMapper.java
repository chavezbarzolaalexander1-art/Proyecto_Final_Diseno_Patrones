
package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.ImpuestoRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.ImpuestoResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.Impuesto;

@Mapper(componentModel = "spring")
public interface ImpuestoMapper {
    ImpuestoResponse toResponse(Impuesto impuesto);

    @Mapping(target = "id", ignore = true)
    Impuesto toEntity(ImpuestoRequest request);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromRequest(ImpuestoRequest request, @MappingTarget Impuesto impuesto);
}