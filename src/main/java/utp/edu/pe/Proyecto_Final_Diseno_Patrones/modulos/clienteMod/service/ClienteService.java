package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.service;

import java.util.List;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.ClienteRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.ClienteResponse;

/**
 * Servicio que define las operaciones de gestión de clientes.
 */
public interface ClienteService {

  /**
   * Lista todos los clientes registrados.
   *
   * @return Lista de clientes.
   */
  List<ClienteResponse> listarClientes();

  /**
   * Busca un cliente por su identificador.
   *
   * @param id Identificador del cliente.
   * @return Datos del cliente encontrado.
   */
  ClienteResponse buscarPorId(Long id);

  /**
   * Registra un nuevo cliente.
   *
   * @param request Datos del cliente a registrar.
   * @return Datos del cliente registrado.
   */
  ClienteResponse guardarCliente(ClienteRequest request);

  /**
   * Actualiza los datos de un cliente existente.
   *
   * @param id Identificador del cliente.
   * @param request Datos actualizados del cliente.
   * @return Datos del cliente actualizado.
   */
  ClienteResponse actualizarCliente(Long id, ClienteRequest request);

  /**
   * Elimina un cliente por su identificador.
   *
   * @param id Identificador del cliente.
   */
  void eliminarCliente(Long id);
}

