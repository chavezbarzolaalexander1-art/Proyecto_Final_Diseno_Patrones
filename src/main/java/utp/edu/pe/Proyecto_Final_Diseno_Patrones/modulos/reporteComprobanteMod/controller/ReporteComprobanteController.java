package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.reporteComprobanteMod.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.reporteComprobanteMod.dto.ArchivoReporte;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.reporteComprobanteMod.service.ReporteComprobanteService;

/**
 * Controlador REST encargado de exponer los endpoints para la generación y descarga
 * de reportes de comprobantes de pago en diferentes formatos (PDF, XML, etc.).
 */
@RestController
@RequestMapping("/api/reportes/comprobantes")
@RequiredArgsConstructor
public class ReporteComprobanteController {

  private final ReporteComprobanteService reporteService;

  /**
   * Descarga o visualiza en línea el reporte de un comprobante en el formato especificado.
   *
   * @param comprobanteId identificador único del comprobante de pago
   * @param formato formato de exportación deseado (por ejemplo, "PDF" o "XML"); por defecto "PDF"
   * @param inline indica si el archivo debe abrirse en el navegador ({@code true}) o descargarse
   *     como adjunto ({@code false})
   * @return respuesta HTTP con el contenido binario del archivo y sus cabeceras correspondientes
   */
  @GetMapping("/{comprobanteId}")
  public ResponseEntity<byte[]> descargarPorFormato(
      @PathVariable Long comprobanteId,
      @RequestParam(defaultValue = "PDF") String formato,
      @RequestParam(defaultValue = "false") boolean inline
  ) {
    ArchivoReporte archivo = reporteService.exportarComprobante(comprobanteId, formato);

    ContentDisposition disposicion = (inline ? ContentDisposition.inline() :
        ContentDisposition.attachment())
        .filename(archivo.nombreArchivo())
        .build();

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, disposicion.toString())
        .contentType(MediaType.parseMediaType(archivo.contentType()))
        .body(archivo.contenido());
  }

  /**
   * Descarga directamente el reporte de un comprobante en formato PDF.
   *
   * @param comprobanteId identificador único del comprobante de pago
   * @return respuesta HTTP con el archivo PDF adjunto
   */
  @GetMapping("/{comprobanteId}/pdf")
  public ResponseEntity<byte[]> descargarPdf(@PathVariable Long comprobanteId) {
    return descargarPorFormato(comprobanteId, "PDF", false);
  }

  /**
   * Descarga directamente el reporte de un comprobante en formato XML.
   *
   * @param comprobanteId identificador único del comprobante de pago
   * @return respuesta HTTP con el archivo XML adjunto
   */
  @GetMapping("/{comprobanteId}/xml")
  public ResponseEntity<byte[]> descargarXml(@PathVariable Long comprobanteId) {
    return descargarPorFormato(comprobanteId, "XML", false);
  }
}