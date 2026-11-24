package utp.edu.pe.Proyecto_Final_Diseno_Patrones.TestProyectoFinal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.CategoriaRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.CategoriaResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.ImpuestoRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.ImpuestoResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.ProductoRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.ProductoResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.UnidadMedidaRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.UnidadMedidaResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.CategoriaService;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.ImpuestoService;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.ProductoService;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.StockInventarioService;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.UnidadMedidaService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class ProductoStockServiceTest {
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

  private ProductoResponse crearProductoBase(String codigo, int stockInicial) {
    Long catId = categoriaService.registrarCategoria(new CategoriaRequest("Cat-" + codigo, "Desc")).id();
    Long umId = unidadMedidaService.registrarUnidadMedida(new UnidadMedidaRequest("U-" + codigo, "Unidad")).id();
    Long igvId = impuestoService.registrarImpuesto(new ImpuestoRequest("IGV-" + codigo, 18.0)).id();

    return productoService.registrarProducto(new ProductoRequest(
        codigo, "Botella " + codigo, 2.50, catId, umId, igvId, stockInicial, 10
    ));
  }

  @Test
  void registrarYBuscarProductoPorIdYCodigo() {
    ProductoResponse creado = crearProductoBase("PET-01", 40);

    assertEquals("PET-01", productoService.buscarPorId(creado.id()).codigo());
    assertEquals(creado.id(), productoService.buscarPorCodigo("PET-01").id());
    assertFalse(productoService.listarTodos().isEmpty());
  }

  @Test
  void registrarProductoConCodigoDuplicadoLanzaExcepcion() {
    ProductoResponse creado = crearProductoBase("PET-02", 20);
    ProductoRequest duplicado = new ProductoRequest(
        "PET-02", "Otro nombre", 3.0, creado.categoriaId(),
        creado.unidadMedidaId(), creado.impuestoId(), 10, 5
    );

    assertThrows(IllegalArgumentException.class, () -> productoService.registrarProducto(duplicado));
  }

  @Test
  void actualizarProductoModificaDatosYStock() {
    ProductoResponse creado = crearProductoBase("PET-03", 20);
    ProductoRequest editado = new ProductoRequest(
        "PET-03", "Botella Editada", 4.0, creado.categoriaId(),
        creado.unidadMedidaId(), creado.impuestoId(), 35, 8
    );

    ProductoResponse res = productoService.actualizarProducto(creado.id(), editado);

    assertEquals("Botella Editada", res.nombre());
    assertEquals(35, stockService.buscarPorProductoId(creado.id()).cantidadDisponible());
  }

  @Test
  void eliminarProductoRemueveDelCatalogo() {
    ProductoResponse creado = crearProductoBase("PET-04", 15);
    Long productoId = creado.id();

    productoService.eliminarProducto(productoId);

    assertThrows(RuntimeException.class, () -> productoService.buscarPorId(productoId));
  }

  @Test
  void descontarStockReduceYDetectaAlertaStockBajo() {
    ProductoResponse creado = crearProductoBase("PET-05", 25);
    Long productoId = creado.id();

    stockService.descontarStock(productoId, 18);

    assertEquals(7, stockService.buscarPorProductoId(productoId).cantidadDisponible());
    assertTrue(stockService.listarProductosConStockBajo().stream()
        .anyMatch(s -> s.productoId().equals(productoId)));
    assertThrows(IllegalArgumentException.class, () -> stockService.descontarStock(productoId, 50));
  }

  @Test
  void restaurarYActualizarManualmenteStock() {
    ProductoResponse creado = crearProductoBase("PET-06", 10);
    Long productoId = creado.id();

    stockService.restaurarStock(productoId, 15);
    assertEquals(25, stockService.buscarPorProductoId(productoId).cantidadDisponible());

    stockService.actualizarCantidadStock(productoId, 60);
    assertEquals(60, stockService.buscarPorProductoId(productoId).cantidadDisponible());
    assertThrows(IllegalArgumentException.class, () -> stockService.actualizarCantidadStock(productoId, -5));
  }

  @Test
  void crudCategoriaUnidadMedidaEImpuesto() {
    CategoriaResponse cat = categoriaService.registrarCategoria(new CategoriaRequest("Galoneras", "Grandes"));
    categoriaService.actualizarCategoria(cat.id(), new CategoriaRequest("Galoneras HD", "Reforzadas"));
    assertEquals("Galoneras HD", categoriaService.buscarPorId(cat.id()).nombre());
    categoriaService.eliminarCategoria(cat.id());

    UnidadMedidaResponse um = unidadMedidaService.registrarUnidadMedida(new UnidadMedidaRequest("BX", "Caja"));
    unidadMedidaService.actualizarUnidadMedida(um.id(), new UnidadMedidaRequest("BX", "Caja x 50"));
    assertEquals("Caja x 50", unidadMedidaService.buscarPorId(um.id()).descripcion());
    unidadMedidaService.eliminarUnidadMedida(um.id());

    ImpuestoResponse imp = impuestoService.registrarImpuesto(new ImpuestoRequest("EXONERADO", 0.0));
    impuestoService.actualizarImpuesto(imp.id(), new ImpuestoRequest("INAFECTO", 0.0));
    assertEquals("INAFECTO", impuestoService.buscarPorId(imp.id()).nombre());
    impuestoService.eliminarImpuesto(imp.id());
  }
}