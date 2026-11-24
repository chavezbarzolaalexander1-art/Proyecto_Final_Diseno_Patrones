package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.CategoriaRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.CategoriaResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.Categoria;

/**
 * Mapper de MapStruct encargado de convertir entre la entidad {@link Categoria}
 * y sus respectivos DTO de solicitud y respuesta.
 */
@Mapper(componentModel = "spring")
public interface CategoriaMapper {

  /**
   * Convierte una entidad {@link Categoria} en un DTO {@link CategoriaResponse}.
   *
   * @param categoria entidad de categoría a transformar
   * @return DTO de respuesta con los datos de la categoría
   */
  CategoriaResponse toResponse(Categoria categoria);

  /**
   * Convierte un DTO {@link CategoriaRequest} en una nueva entidad {@link Categoria}.
   *
   * @param request DTO de solicitud con los datos de entrada
   * @return nueva instancia de {@link Categoria} sin identificador asignado
   */
  @Mapping(target = "id", ignore = true)
  Categoria toEntity(CategoriaRequest request);

  /**
   * Actualiza los atributos de una entidad {@link Categoria} existente a partir de un DTO
   * {@link CategoriaRequest}.
   *
   * @param request DTO de solicitud con los nuevos datos
   * @param categoria entidad de categoría existente a actualizar
   */
  @Mapping(target = "id", ignore = true)
  void updateEntityFromRequest(CategoriaRequest request, @MappingTarget Categoria categoria);
}