package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.reporteComprobanteMod.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.reporteComprobanteMod.dto.ArchivoReporte;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.reporteComprobanteMod.service.ReporteComprobanteService;

@RestController
@RequestMapping("/api/reportes/comprobantes")
@RequiredArgsConstructor
public class ReporteComprobanteController {

    private final ReporteComprobanteService reporteService;

    @GetMapping("/{comprobanteId}")
    public ResponseEntity<byte[]> descargarPorFormato(
            @PathVariable Long comprobanteId,
            @RequestParam(defaultValue = "PDF") String formato,
            @RequestParam(defaultValue = "false") boolean inline
    ) {
        ArchivoReporte archivo = reporteService.exportarComprobante(comprobanteId, formato);

        ContentDisposition disposicion = (inline ? ContentDisposition.inline() : ContentDisposition.attachment())
                .filename(archivo.nombreArchivo())
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, disposicion.toString())
                .contentType(MediaType.parseMediaType(archivo.contentType()))
                .body(archivo.contenido());
    }

    @GetMapping("/{comprobanteId}/pdf")
    public ResponseEntity<byte[]> descargarPdf(@PathVariable Long comprobanteId) {
        return descargarPorFormato(comprobanteId, "PDF", false);
    }

    @GetMapping("/{comprobanteId}/xml")
    public ResponseEntity<byte[]> descargarXml(@PathVariable Long comprobanteId) {
        return descargarPorFormato(comprobanteId, "XML", false);
    }
}