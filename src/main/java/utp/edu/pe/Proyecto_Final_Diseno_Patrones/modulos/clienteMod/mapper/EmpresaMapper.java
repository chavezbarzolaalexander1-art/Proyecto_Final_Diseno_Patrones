package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.EmpresaRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.EmpresaResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model.Empresa;

/**
 * Mapper para convertir entre entidades y DTO de empresa.
 */
@Mapper(componentModel = "spring")
public interface EmpresaMapper {
  /**
   * Convierte una entidad empresa en su DTO de respuesta.
   *
   * @param empresa Entidad de la empresa.
   * @return DTO de respuesta de la empresa.
   */
  EmpresaResponse toResponse(Empresa empresa);

  /**
   * Convierte una solicitud en una entidad empresa.
   *
   * @param request DTO de solicitud de la empresa.
   * @return Entidad empresa.
   */
  @Mapping(target = "id", ignore = true)
  Empresa toEntity(EmpresaRequest request);

  /**
   * Actualiza una entidad empresa con los datos de una solicitud.
   *
   * @param request DTO con los datos actualizados.
   * @param empresa Entidad empresa que será actualizada.
   */
  @Mapping(target = "id", ignore = true)
  void updateEntityFromRequest(EmpresaRequest request, @MappingTarget Empresa empresa);
}
