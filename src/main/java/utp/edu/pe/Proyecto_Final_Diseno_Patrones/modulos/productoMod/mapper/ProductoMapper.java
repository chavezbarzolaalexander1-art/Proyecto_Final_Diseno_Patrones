package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.ProductoResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.StockInventarioResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.Producto;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.StockInventario;

/**
 * Mapper de MapStruct encargado de convertir entre las entidades {@link Producto} y
 * {@link StockInventario} hacia sus respectivos DTOs de respuesta.
 */
@Mapper(componentModel = "spring")
public interface ProductoMapper {
  /**
   * Convierte una entidad {@link Producto} en un DTO {@link ProductoResponse},
   * aplanando los datos de categoría, unidad de medida, impuesto y stock de inventario.
   *
   * @param producto entidad de producto a transformar
   * @return DTO de respuesta con la información completa del producto
   */
  @Mapping(source = "categoria.id", target = "categoriaId")
  @Mapping(source = "categoria.nombre", target = "categoriaNombre")
  @Mapping(source = "unidadMedida.id", target = "unidadMedidaId")
  @Mapping(source = "unidadMedida.codigo", target = "unidadMedidaCodigo")
  @Mapping(source = "unidadMedida.descripcion", target = "unidadMedidaDescripcion")
  @Mapping(source = "impuesto.id", target = "impuestoId")
  @Mapping(source = "impuesto.nombre", target = "impuestoNombre")
  @Mapping(source = "impuesto.porcentaje", target = "impuestoPorcentaje")
  @Mapping(source = "stockInventario.cantidadDisponible", target = "cantidadDisponible")
  @Mapping(source = "stockInventario.stockMinimo", target = "stockMinimo")
  ProductoResponse toResponse(Producto producto);

  /**
   * Convierte una entidad {@link StockInventario} en un DTO {@link StockInventarioResponse},
   * incluyendo los datos identificativos del producto asociado.
   *
   * @param stock entidad de stock de inventario a transformar
   * @return DTO de respuesta con el estado actual del inventario del producto
   */
  @Mapping(source = "producto.id", target = "productoId")
  @Mapping(source = "producto.codigo", target = "codigoProducto")
  @Mapping(source = "producto.nombre", target = "nombreProducto")
  StockInventarioResponse toStockResponse(StockInventario stock);
}