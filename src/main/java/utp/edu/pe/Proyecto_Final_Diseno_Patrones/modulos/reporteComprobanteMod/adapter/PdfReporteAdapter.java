package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.reporteComprobanteMod.adapter;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import org.springframework.stereotype.Component;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model.Empresa;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.ComprobantePago;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.DetalleComprobante;

/**
 * Patrón Adapter (ConcreteAdapter): adapta la API de OpenPDF (com.lowagie.text)
 * a la interfaz GeneradorReporteComprobante.
 */
@Component
public class PdfReporteAdapter implements GeneradorReporteComprobante {

  private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy " +
      "HH:mm:ss");

  @Override
  public String formato() {
    return "PDF";
  }

  @Override
  public String contentType() {
    return "application/pdf";
  }

  @Override
  public String extension() {
    return "pdf";
  }

  @Override
  public byte[] generar(ComprobantePago comprobante, Empresa empresaEmisora) {
    try (ByteArrayOutputStream salida = new ByteArrayOutputStream()) {
      Document documento = new Document(PageSize.A4, 36, 36, 36, 36);
      PdfWriter.getInstance(documento, salida);
      documento.open();

      Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, Color.WHITE);
      Font fuenteSubtitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, Color.DARK_GRAY);
      Font fuenteNormal = FontFactory.getFont(FontFactory.HELVETICA, 9, Color.BLACK);
      Font fuenteNegrita = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9, Color.BLACK);

      // 1. Cabecera Empresa + Recuadro Fiscal
      PdfPTable tablaCabecera = new PdfPTable(new float[]{60f, 40f});
      tablaCabecera.setWidthPercentage(100);

      String nombreEmpresa = empresaEmisora != null ? empresaEmisora.getRazonSocial() : "EMPRESA " +
          "EMISORA S.A.C.";
      String rucEmpresa = empresaEmisora != null ? empresaEmisora.getRuc() : "20000000001";
      String dirEmpresa = empresaEmisora != null ? empresaEmisora.getDireccionFiscal() : "Lima, " +
          "Perú";

      PdfPCell celdaEmisor = new PdfPCell();
      celdaEmisor.setBorder(Rectangle.NO_BORDER);
      celdaEmisor.addElement(new Paragraph(nombreEmpresa, fuenteSubtitulo));
      celdaEmisor.addElement(new Paragraph("Dirección: " + dirEmpresa, fuenteNormal));
      if (empresaEmisora != null && empresaEmisora.getTelefono() != null) {
        celdaEmisor.addElement(new Paragraph("Teléfono: " + empresaEmisora.getTelefono(),
            fuenteNormal));
      }
      tablaCabecera.addCell(celdaEmisor);

      PdfPCell celdaFiscal = new PdfPCell();
      celdaFiscal.setBackgroundColor(new Color(30, 58, 95));
      celdaFiscal.setPadding(10);
      celdaFiscal.setHorizontalAlignment(Element.ALIGN_CENTER);
      Paragraph pRuc = new Paragraph("RUC: " + rucEmpresa, fuenteTitulo);
      pRuc.setAlignment(Element.ALIGN_CENTER);
      Paragraph pTipo = new Paragraph(comprobante.getTituloDocumento(), fuenteTitulo);
      pTipo.setAlignment(Element.ALIGN_CENTER);
      Paragraph pSerie = new Paragraph(comprobante.getSerie() + " - " + comprobante.getNumero(),
          fuenteTitulo);
      pSerie.setAlignment(Element.ALIGN_CENTER);
      celdaFiscal.addElement(pRuc);
      celdaFiscal.addElement(pTipo);
      celdaFiscal.addElement(pSerie);
      tablaCabecera.addCell(celdaFiscal);

      documento.add(tablaCabecera);
      documento.add(Chunk.NEWLINE);

      // 2. Datos del Cliente y Comprobante
      PdfPTable tablaDatos = new PdfPTable(new float[]{25f, 75f});
      tablaDatos.setWidthPercentage(100);
      agregarFilaClaveValor(tablaDatos, "Cliente:",
          comprobante.getCliente().getRazonSocialONombre(), fuenteNegrita, fuenteNormal);
      agregarFilaClaveValor(tablaDatos, comprobante.getCliente().getTipoDocumento().name() + ":",
          comprobante.getCliente().getNumeroDocumento(), fuenteNegrita, fuenteNormal);
      if (comprobante.getCliente().getDireccion() != null) {
        agregarFilaClaveValor(tablaDatos, "Dirección:",
            comprobante.getCliente().getDireccion().getDireccion(), fuenteNegrita, fuenteNormal);
      }
      agregarFilaClaveValor(tablaDatos, "Fecha Emisión:",
          comprobante.getFechaEmision().format(FORMATO_FECHA), fuenteNegrita, fuenteNormal);
      agregarFilaClaveValor(tablaDatos, "Vendedor:",
          comprobante.getVendedor().getNombres() + " " + comprobante.getVendedor().getApellidos()
          , fuenteNegrita, fuenteNormal);
      agregarFilaClaveValor(tablaDatos, "Estado Fiscal / Pago:",
          comprobante.getEstadoComprobante().name() + " / " + comprobante.getEstadoPago().name(),
          fuenteNegrita, fuenteNormal);

      if (comprobante.getCondicionPago() != null) {
        agregarFilaClaveValor(tablaDatos, "Condición de Pago:",
            comprobante.getCondicionPago().getDescripcion(), fuenteNegrita, fuenteNormal);
      }

      // Datos polimórficos específicos de Boleta o Factura
      for (Map.Entry<String, String> extra :
          comprobante.getDatosAdicionalesDocumento().entrySet()) {
        agregarFilaClaveValor(tablaDatos, extra.getKey() + ":", extra.getValue(), fuenteNegrita,
            fuenteNormal);
      }

      documento.add(tablaDatos);
      documento.add(Chunk.NEWLINE);

      // 3. Tabla de Detalles
      PdfPTable tablaDetalles = new PdfPTable(new float[]{15f, 45f, 12f, 14f, 14f});
      tablaDetalles.setWidthPercentage(100);

      String[] encabezados = {"Código", "Descripción", "Cant.", "P. Unit.", "Subtotal"};
      for (String h : encabezados) {
        PdfPCell header = new PdfPCell(new Phrase(h, fuenteTitulo));
        header.setBackgroundColor(new Color(52, 73, 94));
        header.setPadding(6);
        header.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablaDetalles.addCell(header);
      }

      for (DetalleComprobante det : comprobante.getDetalles()) {
        tablaDetalles.addCell(celdaTexto(det.getProducto().getCodigo(), fuenteNormal,
            Element.ALIGN_CENTER));
        tablaDetalles.addCell(celdaTexto(det.getProducto().getNombre(), fuenteNormal,
            Element.ALIGN_LEFT));
        tablaDetalles.addCell(celdaTexto(String.valueOf(det.getCantidad()), fuenteNormal,
            Element.ALIGN_RIGHT));
        tablaDetalles.addCell(celdaTexto("S/ " + det.getPrecioUnitario(), fuenteNormal,
            Element.ALIGN_RIGHT));
        tablaDetalles.addCell(celdaTexto("S/ " + det.getSubtotal(), fuenteNormal,
            Element.ALIGN_RIGHT));
      }

      documento.add(tablaDetalles);
      documento.add(Chunk.NEWLINE);

      // 4. Totales
      PdfPTable tablaTotales = new PdfPTable(new float[]{72f, 14f, 14f});
      tablaTotales.setWidthPercentage(100);
      agregarFilaTotal(tablaTotales, "OP. GRAVADA (SUBTOTAL):", "S/ " + comprobante.getSubtotal()
          , fuenteNegrita, fuenteNormal);
      agregarFilaTotal(tablaTotales, "IGV (18%):", "S/ " + comprobante.getIgv(), fuenteNegrita,
          fuenteNormal);
      agregarFilaTotal(tablaTotales, "IMPORTE TOTAL:", "S/ " + comprobante.getTotal(),
          fuenteNegrita, fuenteNegrita);

      documento.add(tablaTotales);
      documento.close();

      return salida.toByteArray();
    } catch (Exception ex) {
      throw new IllegalStateException("Error al generar el reporte PDF con OpenPDF: " + ex.getMessage(), ex);
    }
  }

  private void agregarFilaClaveValor(PdfPTable tabla, String clave, String valor, Font fClave,
                                     Font fValor) {
    PdfPCell c1 = new PdfPCell(new Phrase(clave, fClave));
    c1.setBorder(Rectangle.NO_BORDER);
    c1.setPadding(3);
    PdfPCell c2 = new PdfPCell(new Phrase(valor != null ? valor : "-", fValor));
    c2.setBorder(Rectangle.NO_BORDER);
    c2.setPadding(3);
    tabla.addCell(c1);
    tabla.addCell(c2);
  }

  private PdfPCell celdaTexto(String texto, Font fuente, int alineacion) {
    PdfPCell celda = new PdfPCell(new Phrase(texto, fuente));
    celda.setPadding(5);
    celda.setHorizontalAlignment(alineacion);
    return celda;
  }

  private void agregarFilaTotal(PdfPTable tabla, String etiqueta, String monto, Font fEtiqueta,
                                Font fMonto) {
    PdfPCell vacia = new PdfPCell(new Phrase(""));
    vacia.setBorder(Rectangle.NO_BORDER);
    tabla.addCell(vacia);

    PdfPCell cEtiqueta = new PdfPCell(new Phrase(etiqueta, fEtiqueta));
    cEtiqueta.setHorizontalAlignment(Element.ALIGN_RIGHT);
    cEtiqueta.setPadding(4);
    tabla.addCell(cEtiqueta);

    PdfPCell cMonto = new PdfPCell(new Phrase(monto, fMonto));
    cMonto.setHorizontalAlignment(Element.ALIGN_RIGHT);
    cMonto.setPadding(4);
    tabla.addCell(cMonto);
  }
}