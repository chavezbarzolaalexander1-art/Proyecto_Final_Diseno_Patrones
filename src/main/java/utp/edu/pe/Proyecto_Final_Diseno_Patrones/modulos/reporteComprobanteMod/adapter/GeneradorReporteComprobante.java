package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.reporteComprobanteMod.adapter;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model.Empresa;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.ComprobantePago;

/**
 * Patrón Adapter (Target): interfaz uniforme para exportar comprobantes
 * hacia distintos formatos externos (PDF, XML, etc.).
 */
public interface GeneradorReporteComprobante {

    /** Formato soportado en mayúsculas (ej. "PDF", "XML"). */
    String formato();

    /** MIME type HTTP para la descarga (ej. "application/pdf"). */
    String contentType();

    /** Extensión del archivo sin punto (ej. "pdf", "xml"). */
    String extension();

    /** Adapta la entidad de dominio al formato binario de salida. */
    byte[] generar(ComprobantePago comprobante, Empresa empresaEmisora);
}