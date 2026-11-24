package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.VendedorRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.VendedorResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model.Vendedor;

/**
 * Mapper para convertir entre entidades y DTO de vendedor.
 */
@Mapper(componentModel = "spring")
public interface VendedorMapper {

  /**
   * Convierte una entidad vendedor en su DTO de respuesta.
   *
   * @param vendedor Entidad del vendedor.
   * @return DTO de respuesta del vendedor.
   */
  VendedorResponse toResponse(Vendedor vendedor);

  /**
   * Convierte una solicitud en una entidad vendedor.
   *
   * @param request DTO de solicitud del vendedor.
   * @return Entidad vendedor.
   */
  @Mapping(target = "id", ignore = true)
  Vendedor toEntity(VendedorRequest request);

  /**
   * Actualiza una entidad vendedor con los datos de una solicitud.
   *
   * @param request DTO con los datos actualizados.
   * @param vendedor Entidad vendedor que será actualizada.
   */
  @Mapping(target = "id", ignore = true)
  void updateEntityFromRequest(VendedorRequest request, @MappingTarget Vendedor vendedor);
}
