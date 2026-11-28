package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.ClienteRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.ClienteResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.DireccionDto;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model.Cliente;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model.DireccionCliente;

/**
 * Mapper para convertir entre entidades y DTOs de cliente.
 */
@Mapper(componentModel = "spring")
public interface ClienteMapper {
  /**
   * Convierte una entidad cliente en su DTO de respuesta.
   *
   * @param cliente Entidad del cliente.
   * @return DTO de respuesta del cliente.
   */
  @Mapping(source = "documentoIdentidad.numeroDocumento", target = "numeroDocumento")
  @Mapping(source = "documentoIdentidad.tipoDocumento", target = "tipoDocumento")
  @Mapping(source = "datosContacto.email", target = "email")
  @Mapping(source = "datosContacto.telefono", target = "telefono")
  ClienteResponse toResponse(Cliente cliente);

  /**
   * Convierte una solicitud en una entidad cliente.
   *
   * @param request DTO de solicitud del cliente.
   * @return Entidad cliente.
   */
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "documentoIdentidad.tipoDocumento", source = "tipoDocumento")
  @Mapping(target = "documentoIdentidad.numeroDocumento", source = "numeroDocumento")
  @Mapping(target = "datosContacto.email", source = "email")
  @Mapping(target = "datosContacto.telefono", source = "telefono")
  Cliente toEntity(ClienteRequest request);

  /**
   * Convierte una dirección de cliente en un DTO.
   *
   * @param direccion Dirección del cliente.
   * @return DTO de dirección.
   */
  DireccionDto toDireccionDto(DireccionCliente direccion);

  /**
   * Convierte un DTO en una dirección de cliente.
   *
   * @param dto DTO de dirección.
   * @return Dirección del cliente.
   */
  DireccionCliente toDireccionCliente(DireccionDto dto);

  /**
   * Actualiza una entidad cliente con los datos de una solicitud.
   *
   * @param request DTO con los datos actualizados.
   * @param cliente Entidad cliente que será actualizada.
   */
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "documentoIdentidad.tipoDocumento", source = "tipoDocumento")
  @Mapping(target = "documentoIdentidad.numeroDocumento", source = "numeroDocumento")
  @Mapping(target = "datosContacto.email", source = "email")
  @Mapping(target = "datosContacto.telefono", source = "telefono")
  void updateEntityFromRequest(ClienteRequest request, @MappingTarget Cliente cliente);
}
