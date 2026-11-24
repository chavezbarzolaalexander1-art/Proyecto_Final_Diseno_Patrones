package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.UnidadMedidaRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.UnidadMedidaResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.UnidadMedida;

/**
 * Mapper de MapStruct encargado de convertir entre la entidad {@link UnidadMedida}
 * y sus respectivos DTO de solicitud y respuesta.
 */
@Mapper(componentModel = "spring")
public interface UnidadMedidaMapper {

  /**
   * Convierte una entidad {@link UnidadMedida} en un DTO {@link UnidadMedidaResponse}.
   *
   * @param unidadMedida entidad de unidad de medida a transformar
   * @return DTO de respuesta con los datos de la unidad de medida
   */
  UnidadMedidaResponse toResponse(UnidadMedida unidadMedida);

  /**
   * Convierte un DTO {@link UnidadMedidaRequest} en una nueva entidad {@link UnidadMedida}.
   *
   * @param request DTO de solicitud con los datos de entrada
   * @return nueva instancia de {@link UnidadMedida} sin identificador asignado
   */
  @Mapping(target = "id", ignore = true)
  UnidadMedida toEntity(UnidadMedidaRequest request);

  /**
   * Actualiza los atributos de una entidad {@link UnidadMedida} existente a partir de un DTO
   * {@link UnidadMedidaRequest}.
   *
   * @param request DTO de solicitud con los nuevos datos
   * @param unidadMedida entidad de unidad de medida existente a actualizar
   */
  @Mapping(target = "id", ignore = true)
  void updateEntityFromRequest(UnidadMedidaRequest request,
                               @MappingTarget UnidadMedida unidadMedida);
}