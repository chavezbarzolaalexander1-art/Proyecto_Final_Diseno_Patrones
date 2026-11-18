// CategoriaMapper.java
package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.CategoriaRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.CategoriaResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.Categoria;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    CategoriaResponse toResponse(Categoria categoria);

    @Mapping(target = "id", ignore = true)
    Categoria toEntity(CategoriaRequest request);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromRequest(CategoriaRequest request, @MappingTarget Categoria categoria);
}