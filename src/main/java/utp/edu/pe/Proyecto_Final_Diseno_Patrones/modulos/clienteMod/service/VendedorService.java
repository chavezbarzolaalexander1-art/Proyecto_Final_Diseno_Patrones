package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.service;

import java.util.List;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.VendedorRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.VendedorResponse;

/**
 * Servicio que define las operaciones de gestión de vendedores.
 */
public interface VendedorService {

  /**
   * Lista todos los vendedores registrados.
   *
   * @return Lista de vendedores.
   */
  List<VendedorResponse> listarVendedores();

  /**
   * Busca un vendedor por su identificador.
   *
   * @param id Identificador del vendedor.
   * @return Datos del vendedor encontrado.
   */
  VendedorResponse buscarPorId(Long id);

  /**
   * Registra un nuevo vendedor.
   *
   * @param request Datos del vendedor a registrar.
   * @return Datos del vendedor registrado.
   */
  VendedorResponse guardarVendedor(VendedorRequest request);

  /**
   * Actualiza los datos de un vendedor existente.
   *
   * @param id Identificador del vendedor.
   * @param request Datos actualizados del vendedor.
   * @return Datos del vendedor actualizado.
   */
  VendedorResponse actualizarVendedor(Long id, VendedorRequest request);

  /**
   * Elimina un vendedor por su identificador.
   *
   * @param id Identificador del vendedor.
   */
  void eliminarVendedor(Long id);
}

