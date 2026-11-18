package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.reporteComprobanteMod.adapter;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model.Empresa;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.ComprobantePago;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.DetalleComprobante;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * Patrón Adapter (ConcreteAdapter): adapta la API estándar W3C DOM / JAXP Transformer
 * a la interfaz GeneradorReporteComprobante para emitir el XML electrónico.
 */
@Component
public class XmlReporteAdapter implements GeneradorReporteComprobante {

    @Override
    public String formato() {
        return "XML";
    }

    @Override
    public String contentType() {
        return "application/xml";
    }

    @Override
    public String extension() {
        return "xml";
    }

    @Override
    public byte[] generar(ComprobantePago comprobante, Empresa empresaEmisora) {
        try (ByteArrayOutputStream salida = new ByteArrayOutputStream()) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            // Nodo raíz
            Element root = doc.createElement("ComprobanteElectronico");
            root.setAttribute("tipo", comprobante.getTipoComprobante().name());
            root.setAttribute("moneda", "PEN");
            doc.appendChild(root);

            // Cabecera
            Element cabecera = doc.createElement("Cabecera");
            root.appendChild(cabecera);
            agregarElemento(doc, cabecera, "TituloDocumento", comprobante.getTituloDocumento());
            agregarElemento(doc, cabecera, "Serie", comprobante.getSerie());
            agregarElemento(doc, cabecera, "Numero", comprobante.getNumero());
            agregarElemento(doc, cabecera, "FechaEmision",
                    comprobante.getFechaEmision().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            agregarElemento(doc, cabecera, "EstadoComprobante", comprobante.getEstadoComprobante().name());
            agregarElemento(doc, cabecera, "EstadoPago", comprobante.getEstadoPago().name());
            if (comprobante.getCondicionPago() != null) {
                agregarElemento(doc, cabecera, "CondicionPago", comprobante.getCondicionPago().getDescripcion());
            }

            // Datos adicionales polimórficos (Observaciones / OrdenCompra / GuiaRemision)
            Map<String, String> extras = comprobante.getDatosAdicionalesDocumento();
            if (!extras.isEmpty()) {
                Element nodoExtras = doc.createElement("DatosAdicionales");
                cabecera.appendChild(nodoExtras);
                for (Map.Entry<String, String> entry : extras.entrySet()) {
                    Element dato = doc.createElement("Dato");
                    dato.setAttribute("nombre", entry.getKey());
                    dato.setTextContent(entry.getValue());
                    nodoExtras.appendChild(dato);
                }
            }

            // Emisor
            Element emisor = doc.createElement("Emisor");
            root.appendChild(emisor);
            agregarElemento(doc, emisor, "RUC", empresaEmisora != null ? empresaEmisora.getRuc() : "20000000001");
            agregarElemento(doc, emisor, "RazonSocial",
                    empresaEmisora != null ? empresaEmisora.getRazonSocial() : "EMPRESA EMISORA S.A.C.");
            agregarElemento(doc, emisor, "DireccionFiscal",
                    empresaEmisora != null ? empresaEmisora.getDireccionFiscal() : "Lima, Perú");

            // Receptor (Cliente)
            Element receptor = doc.createElement("Receptor");
            root.appendChild(receptor);
            agregarElemento(doc, receptor, "TipoDocumento", comprobante.getCliente().getTipoDocumento().name());
            agregarElemento(doc, receptor, "NumeroDocumento", comprobante.getCliente().getNumeroDocumento());
            agregarElemento(doc, receptor, "RazonSocialONombre", comprobante.getCliente().getRazonSocialONombre());
            if (comprobante.getCliente().getDireccion() != null) {
                agregarElemento(doc, receptor, "Direccion", comprobante.getCliente().getDireccion().getDireccion());
            }

            // Detalles
            Element detallesNode = doc.createElement("Detalles");
            root.appendChild(detallesNode);
            int itemNum = 1;
            for (DetalleComprobante det : comprobante.getDetalles()) {
                Element item = doc.createElement("Item");
                item.setAttribute("linea", String.valueOf(itemNum++));
                detallesNode.appendChild(item);

                agregarElemento(doc, item, "CodigoProducto", det.getProducto().getCodigo());
                agregarElemento(doc, item, "Descripcion", det.getProducto().getNombre());
                agregarElemento(doc, item, "UnidadMedida", det.getProducto().getUnidadMedida().getCodigo());
                agregarElemento(doc, item, "Cantidad", String.valueOf(det.getCantidad()));
                agregarElemento(doc, item, "PrecioUnitario", det.getPrecioUnitario().toPlainString());
                agregarElemento(doc, item, "Subtotal", det.getSubtotal().toPlainString());
            }

            // Totales
            Element totales = doc.createElement("Totales");
            root.appendChild(totales);
            agregarElemento(doc, totales, "Subtotal", comprobante.getSubtotal().toPlainString());
            agregarElemento(doc, totales, "IGV", comprobante.getIgv().toPlainString());
            agregarElemento(doc, totales, "Total", comprobante.getTotal().toPlainString());

            // Serializar árbol DOM a bytes UTF-8 indentados
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            transformer.transform(new DOMSource(doc), new StreamResult(salida));
            return salida.toByteArray();
        } catch (Exception ex) {
            throw new IllegalStateException("Error al generar el comprobante XML: " + ex.getMessage(), ex);
        }
    }

    private void agregarElemento(Document doc, Element padre, String etiqueta, String valor) {
        Element nodo = doc.createElement(etiqueta);
        nodo.setTextContent(valor != null ? valor : "");
        padre.appendChild(nodo);
    }
}