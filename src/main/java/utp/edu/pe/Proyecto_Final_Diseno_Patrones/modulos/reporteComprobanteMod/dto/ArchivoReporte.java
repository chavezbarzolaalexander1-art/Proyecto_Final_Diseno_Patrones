package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.reporteComprobanteMod.dto;

public record ArchivoReporte(
        String nombreArchivo,
        String contentType,
        byte[] contenido
) {
}