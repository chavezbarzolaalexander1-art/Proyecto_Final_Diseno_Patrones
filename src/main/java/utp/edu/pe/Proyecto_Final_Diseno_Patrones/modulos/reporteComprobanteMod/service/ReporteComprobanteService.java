package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.reporteComprobanteMod.service;

import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.reporteComprobanteMod.dto.ArchivoReporte;

public interface ReporteComprobanteService {
    ArchivoReporte exportarComprobante(Long comprobanteId, String formato);
}