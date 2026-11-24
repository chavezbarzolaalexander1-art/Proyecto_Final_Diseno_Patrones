package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.service;

import java.util.List;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model.CondicionPago;

/**
 * Servicio que define las operaciones de negocio para la gestión de condiciones de pago.
 */
public interface CondicionPagoService {

  /**
   * Busca una condición de pago por su descripción o la crea si no existe.
   *
   * @param descripcion descripción de la condición de pago
   * @return condición de pago encontrada o recién creada
   */
  CondicionPago buscarOcrear(String descripcion);

  /**
   * Obtiene la lista completa de condiciones de pago registradas.
   *
   * @return lista de condiciones de pago
   */
  List<CondicionPago> listarTodas();
}
