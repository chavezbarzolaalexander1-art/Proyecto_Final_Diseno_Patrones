// ProductoMapper.java
package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.ProductoResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.StockInventarioResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.Producto;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.StockInventario;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
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

    @Mapping(source = "producto.id", target = "productoId")
    @Mapping(source = "producto.codigo", target = "codigoProducto")
    @Mapping(source = "producto.nombre", target = "nombreProducto")
    StockInventarioResponse toStockResponse(StockInventario stock);
}