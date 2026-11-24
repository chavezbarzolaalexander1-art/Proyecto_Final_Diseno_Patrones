package utp.edu.pe.Proyecto_Final_Diseno_Patrones.TestProyectoFinal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.ClienteRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.DireccionDto;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.EmpresaRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.VendedorRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model.TipoDocumento;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.service.ClienteService;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.service.EmpresaService;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.service.VendedorService;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.dto.ComprobanteResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.dto.CrearComprobanteRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.dto.DetalleComprobanteRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.TipoComprobante;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.service.ComprobanteService;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.CategoriaRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.ImpuestoRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.ProductoRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.UnidadMedidaRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.CategoriaService;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.ImpuestoService;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.ProductoService;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.UnidadMedidaService;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.reporteComprobanteMod.dto.ArchivoReporte;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.reporteComprobanteMod.service.ReporteComprobanteService;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class ReporteComprobanteServiceTest {

  @Autowired
  private EmpresaService empresaService;

  @Autowired
  private ClienteService clienteService;

  @Autowired
  private VendedorService vendedorService;

  @Autowired
  private CategoriaService categoriaService;

  @Autowired
  private UnidadMedidaService unidadMedidaService;

  @Autowired
  private ImpuestoService impuestoService;

  @Autowired
  private ProductoService productoService;

  @Autowired
  private ComprobanteService comprobanteService;

  @Autowired
  private ReporteComprobanteService reporteService;

  private ComprobanteResponse emitirFacturaDePrueba() {
    empresaService.guardarEmpresa(new EmpresaRequest(
        "20501122334", "Plásticos Barzola S.A.C.", "Ate, Lima", "999000111"
    ));
    Long clienteId = clienteService.guardarCliente(new ClienteRequest(
        "Distribuidora Norte SAC", "20445566779", "norte@mail.com", "911223344",
        TipoDocumento.RUC, new DireccionDto("Av. La Molina 300", "Lima", "Lima", "La Molina")
    )).id();
    Long vendedorId = vendedorService.guardarVendedor(
        new VendedorRequest("Ana", "Ramos", "71223344", "V-02")
    ).id();
    Long catId = categoriaService.registrarCategoria(new CategoriaRequest("Tapas", "Accesorios")).id();
    Long umId = unidadMedidaService.registrarUnidadMedida(new UnidadMedidaRequest("NIU", "Unidad")).id();
    Long igvId = impuestoService.registrarImpuesto(new ImpuestoRequest("IGV 18%", 18.0)).id();
    Long prodId = productoService.registrarProducto(new ProductoRequest(
        "TAP-01", "Tapa Rosca 28mm", 0.50, catId, umId, igvId, 200, 20
    )).id();

    return comprobanteService.emitir(new CrearComprobanteRequest(
        TipoComprobante.FACTURA, "F001", "25", clienteId, vendedorId, null,
        List.of(new DetalleComprobanteRequest(prodId, 20)),
        null, "OC-777", "GR-888"
    ));
  }

  @Test
  void exportarComprobanteEnFormatoPdf() {
    ComprobanteResponse factura = emitirFacturaDePrueba();

    ArchivoReporte pdf = reporteService.exportarComprobante(factura.id(), "PDF");

    assertEquals("FACTURA-F001-00000025.pdf", pdf.nombreArchivo());
    assertEquals("application/pdf", pdf.contentType());
    assertTrue(pdf.contenido().length > 0);
  }

  @Test
  void exportarComprobanteEnFormatoXml() {
    ComprobanteResponse factura = emitirFacturaDePrueba();

    ArchivoReporte xml = reporteService.exportarComprobante(factura.id(), "XML");
    String contenidoXml = new String(xml.contenido(), StandardCharsets.UTF_8);

    assertEquals("FACTURA-F001-00000025.xml", xml.nombreArchivo());
    assertEquals("application/xml", xml.contentType());
    assertTrue(contenidoXml.contains("<Serie>F001</Serie>"));
    assertTrue(contenidoXml.contains("OC-777"));
  }

  @Test
  void exportarComprobanteConFormatoNoSoportadoLanzaExcepcion() {
    assertThrows(IllegalArgumentException.class, () -> reporteService.exportarComprobante(1L, "EXCEL"));
  }
}