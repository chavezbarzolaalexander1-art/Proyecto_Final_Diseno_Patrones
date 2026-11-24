package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.service;

import java.util.List;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.EmpresaRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.EmpresaResponse;

/**
 * Servicio que define las operaciones de gestión de empresas.
 */
public interface EmpresaService {

  /**
   * Lista todas las empresas registradas.
   *
   * @return Lista de empresas.
   */
  List<EmpresaResponse> listarEmpresas();

  /**
   * Busca una empresa por su identificador.
   *
   * @param id Identificador de la empresa.
   * @return Datos de la empresa encontrada.
   */
  EmpresaResponse buscarPorId(Long id);

  /**
   * Registra una nueva empresa.
   *
   * @param request Datos de la empresa a registrar.
   * @return Datos de la empresa registrada.
   */
  EmpresaResponse guardarEmpresa(EmpresaRequest request);

  /**
   * Actualiza los datos de una empresa existente.
   *
   * @param id Identificador de la empresa.
   * @param request Datos actualizados de la empresa.
   * @return Datos de la empresa actualizada.
   */
  EmpresaResponse actualizarEmpresa(Long id, EmpresaRequest request);

  /**
   * Elimina una empresa por su identificador.
   *
   * @param id Identificador de la empresa.
   */
  void eliminarEmpresa(Long id);

}
