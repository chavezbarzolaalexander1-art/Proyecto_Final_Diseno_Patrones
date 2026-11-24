package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.reporteComprobanteMod.service;

import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.reporteComprobanteMod.dto.ArchivoReporte;

/**
 * Interfaz de servicio que define las operaciones de negocio para la generación y exportación
 * de reportes de comprobantes de pago en distintos formatos.
 */
public interface ReporteComprobanteService {
  /**
   * Genera y exporta el reporte de un comprobante de pago en el formato solicitado.
   *
   * @param comprobanteId identificador único del comprobante de pago a exportar
   * @param formato formato de salida deseado (por ejemplo, "PDF" o "XML")
   * @return objeto {@link ArchivoReporte} que contiene los metadatos.
   */
  ArchivoReporte exportarComprobante(Long comprobanteId, String formato);
}