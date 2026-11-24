package utp.edu.pe.Proyecto_Final_Diseno_Patrones.TestProyectoFinal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.ClienteRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.DireccionDto;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.VendedorRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model.TipoDocumento;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.service.ClienteService;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.service.VendedorService;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.dto.ComprobanteResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.dto.CrearComprobanteRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.dto.DetalleComprobanteRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.TipoComprobante;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.service.ComprobanteService;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.dto.AnulacionRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.dto.CuotaPagoResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.dto.PagoResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.dto.RegistrarPagoRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model.CondicionPago;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.service.AnulacionComprobanteService;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.service.CondicionPagoService;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.service.CuotaPagoService;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.service.PagoService;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.CategoriaRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.ImpuestoRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.ProductoRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.UnidadMedidaRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.CategoriaService;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.ImpuestoService;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.ProductoService;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.StockInventarioService;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.UnidadMedidaService;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class ComprobantePagoServiceTest {@Autowired
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
  private StockInventarioService stockService;

  @Autowired
  private ComprobanteService comprobanteService;

  @Autowired
  private PagoService pagoService;

  @Autowired
  private AnulacionComprobanteService anulacionService;

  @Autowired
  private CondicionPagoService condicionPagoService;

  @Autowired
  private CuotaPagoService cuotaPagoService;

  private record DatosBase(Long clienteId, Long vendedorId, Long prodId) {
  }

  private DatosBase prepararDatos(String sufijo) {
    Long clienteId = clienteService.guardarCliente(new ClienteRequest(
        "Cliente " + sufijo, "20998877" + sufijo, "c@mail.com", "988776655",
        TipoDocumento.RUC, new DireccionDto("Jr. Unión 200", "Lima", "Lima", "Lima")
    )).id();

    Long vendedorId = vendedorService.guardarVendedor(
        new VendedorRequest("Juan", "Pérez", "70112" + sufijo, "V-" + sufijo)
    ).id();

    Long catId = categoriaService.registrarCategoria(new CategoriaRequest("Cat" + sufijo, "Desc")).id();
    Long umId = unidadMedidaService.registrarUnidadMedida(new UnidadMedidaRequest("U" + sufijo, "Unidad")).id();
    Long igvId = impuestoService.registrarImpuesto(new ImpuestoRequest("IGV" + sufijo, 18.0)).id();

    Long prodId = productoService.registrarProducto(new ProductoRequest(
        "BID-" + sufijo, "Bidón 20L", 10.0, catId, umId, igvId, 100, 10
    )).id();

    return new DatosBase(clienteId, vendedorId, prodId);
  }

  @Test
  void emitirFacturaCalculaIgvYDescuentaStock() {
    DatosBase datos = prepararDatos("101");
    CondicionPago contado = condicionPagoService.buscarOcrear("Contado");

    ComprobanteResponse factura = comprobanteService.emitir(new CrearComprobanteRequest(
        TipoComprobante.FACTURA, "F001", "101", datos.clienteId(), datos.vendedorId(), contado.getId(),
        List.of(new DetalleComprobanteRequest(datos.prodId(), 10)),
        null, "OC-500", "GR-900"
    ));

    assertEquals("00000101", factura.numero());
    assertEquals(new BigDecimal("100.00"), factura.subtotal());
    assertEquals(new BigDecimal("18.00"), factura.igv());
    assertEquals(new BigDecimal("118.00"), factura.total());
    assertEquals(90, stockService.buscarPorProductoId(datos.prodId()).cantidadDisponible());
    assertFalse(comprobanteService.listarTodos().isEmpty());
    assertFalse(condicionPagoService.listarTodas().isEmpty());
  }

  @Test
  void emitirComprobanteDuplicadoLanzaExcepcion() {
    DatosBase datos = prepararDatos("102");
    CrearComprobanteRequest request = new CrearComprobanteRequest(
        TipoComprobante.BOLETA, "B001", "102", datos.clienteId(), datos.vendedorId(), null,
        List.of(new DetalleComprobanteRequest(datos.prodId(), 2)),
        "Obs", null, null
    );

    comprobanteService.emitir(request);
    assertThrows(IllegalArgumentException.class, () -> comprobanteService.emitir(request));
  }

  @Test
  void registrarPagosParcialYTotalCambiaEstadoAPagado() {
    DatosBase datos = prepararDatos("103");
    ComprobanteResponse boleta = comprobanteService.emitir(new CrearComprobanteRequest(
        TipoComprobante.BOLETA, "B001", "103", datos.clienteId(), datos.vendedorId(), null,
        List.of(new DetalleComprobanteRequest(datos.prodId(), 10)),
        null, null, null
    ));
    Long boletaId = boleta.id();

    pagoService.registrarPago(new RegistrarPagoRequest(boletaId, "EFECTIVO", 50.0, null));
    assertEquals("PENDIENTE", comprobanteService.buscarPorId(boletaId).estadoPago());

    PagoResponse segundo = pagoService.registrarPago(new RegistrarPagoRequest(boletaId, "TARJETA", 68.0, "OP-12"));
    assertEquals(68.0, segundo.monto());
    assertEquals(118.0, pagoService.calcularTotalPagado(boletaId));
    assertEquals(2, pagoService.listarPorComprobante(boletaId).size());
    assertEquals("PAGADO", comprobanteService.buscarPorId(boletaId).estadoPago());

    RegistrarPagoRequest pagoExcedente = new RegistrarPagoRequest(boletaId, "EFECTIVO", 10.0, null);
    assertThrows(IllegalArgumentException.class, () -> pagoService.registrarPago(pagoExcedente));
  }

  @Test
  void generarYPagarCuotasDeComprobanteACredito() {
    DatosBase datos = prepararDatos("104");
    ComprobanteResponse factura = comprobanteService.emitir(new CrearComprobanteRequest(
        TipoComprobante.FACTURA, "F001", "104", datos.clienteId(), datos.vendedorId(), null,
        List.of(new DetalleComprobanteRequest(datos.prodId(), 10)),
        null, null, null
    ));
    Long facturaId = factura.id();

    cuotaPagoService.generarCronogramaCuotas(facturaId, 2, 30);
    List<CuotaPagoResponse> cuotas = cuotaPagoService.listarPorComprobante(facturaId);
    assertEquals(2, cuotas.size());

    Long primeraCuotaId = cuotas.get(0).id();
    cuotaPagoService.marcarCuotaComoPagada(primeraCuotaId, "TRANSFERENCIA", "TR-001");
    assertTrue(cuotaPagoService.listarPorComprobante(facturaId).get(0).pagado());

    assertThrows(IllegalArgumentException.class, () ->
        cuotaPagoService.marcarCuotaComoPagada(primeraCuotaId, "EFECTIVO", null)
    );
  }

  @Test
  void anularComprobanteRestauraStockYBloqueaNuevosPagos() {
    DatosBase datos = prepararDatos("105");
    ComprobanteResponse boleta = comprobanteService.emitir(new CrearComprobanteRequest(
        TipoComprobante.BOLETA, "B001", "105", datos.clienteId(), datos.vendedorId(), null,
        List.of(new DetalleComprobanteRequest(datos.prodId(), 15)),
        null, null, null
    ));
    Long boletaId = boleta.id();
    assertEquals(85, stockService.buscarPorProductoId(datos.prodId()).cantidadDisponible());

    anulacionService.anularComprobante(boletaId, new AnulacionRequest("Error de digitación"));

    assertEquals("ANULADO", comprobanteService.buscarPorId(boletaId).estadoComprobante());
    assertEquals(100, stockService.buscarPorProductoId(datos.prodId()).cantidadDisponible());

    AnulacionRequest reintentoAnulacion = new AnulacionRequest("Reintento");
    assertThrows(IllegalArgumentException.class, () ->
        anulacionService.anularComprobante(boletaId, reintentoAnulacion)
    );

    RegistrarPagoRequest pagoEnAnulado = new RegistrarPagoRequest(boletaId, "EFECTIVO", 10.0, null);
    assertThrows(IllegalArgumentException.class, () ->
        pagoService.registrarPago(pagoEnAnulado)
    );
  }
}
