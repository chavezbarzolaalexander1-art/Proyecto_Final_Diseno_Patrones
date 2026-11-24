package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.ImpuestoRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.ImpuestoResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.Impuesto;

/**
 * Mapper de MapStruct encargado de convertir entre la entidad {@link Impuesto}
 * y sus respectivos DTO de solicitud y respuesta.
 */
@Mapper(componentModel = "spring")
public interface ImpuestoMapper {

  /**
   * Convierte una entidad {@link Impuesto} en un DTO {@link ImpuestoResponse}.
   *
   * @param impuesto entidad de impuesto a transformar
   * @return DTO de respuesta con los datos del impuesto
   */
  ImpuestoResponse toResponse(Impuesto impuesto);

  /**
   * Convierte un DTO {@link ImpuestoRequest} en una nueva entidad {@link Impuesto}.
   *
   * @param request DTO de solicitud con los datos de entrada
   * @return nueva instancia de {@link Impuesto} sin identificador asignado
   */
  @Mapping(target = "id", ignore = true)
  Impuesto toEntity(ImpuestoRequest request);

  /**
   * Actualiza los atributos de una entidad {@link Impuesto} existente a partir de un DTO
   * {@link ImpuestoRequest}.
   *
   * @param request DTO de solicitud con los nuevos datos
   * @param impuesto entidad de impuesto existente a actualizar
   */
  @Mapping(target = "id", ignore = true)
  void updateEntityFromRequest(ImpuestoRequest request, @MappingTarget Impuesto impuesto);
}