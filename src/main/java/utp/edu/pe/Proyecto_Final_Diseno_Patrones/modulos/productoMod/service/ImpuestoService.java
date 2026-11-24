package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service;

import java.util.List;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.ImpuestoRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.ImpuestoResponse;

/**
 * Interfaz de servicio que define las operaciones de negocio para la gestión de impuestos
 * aplicables a los productos.
 */
public interface ImpuestoService {

  /**
   * Registra un nuevo impuesto en el sistema.
   *
   * @param request DTO con los datos del impuesto a registrar
   * @return DTO de respuesta con los datos del impuesto creado
   */
  ImpuestoResponse registrarImpuesto(ImpuestoRequest request);

  /**
   * Obtiene el listado completo de todos los impuestos registrados.
   *
   * @return lista de DTOs de respuesta con los impuestos disponibles
   */
  List<ImpuestoResponse> listarTodos();

  /**
   * Busca y recupera un impuesto específico mediante su identificador único.
   *
   * @param id identificador único del impuesto
   * @return DTO de respuesta con los datos del impuesto encontrado
   */
  ImpuestoResponse buscarPorId(Long id);

  /**
   * Actualiza los datos de un impuesto existente.
   *
   * @param id identificador único del impuesto a modificar
   * @param request DTO con los nuevos datos del impuesto
   * @return DTO de respuesta con la información actualizada
   */
  ImpuestoResponse actualizarImpuesto(Long id, ImpuestoRequest request);

  /**
   * Elimina un impuesto del sistema según su identificador único.
   *
   * @param id identificador único del impuesto a eliminar
   */
  void eliminarImpuesto(Long id);
}