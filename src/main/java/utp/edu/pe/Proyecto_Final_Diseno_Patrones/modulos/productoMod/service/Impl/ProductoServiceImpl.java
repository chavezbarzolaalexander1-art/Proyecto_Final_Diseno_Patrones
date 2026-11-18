// ProductoServiceImpl.java
package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.ProductoRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto.ProductoResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.mapper.ProductoMapper;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.*;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.repository.*;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.ProductoService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final UnidadMedidaRepository unidadMedidaRepository;
    private final ImpuestoRepository impuestoRepository;
    private final ProductoMapper productoMapper;

    /**
     * Registra un nuevo producto en el sistema e inicializa su stock predeterminado si no fue especificado.
     * @param request Objeto Producto con la información a registrar.
     * @return El producto guardado en la base de datos.
     */
    @Override
    @Transactional
    public ProductoResponse registrarProducto(ProductoRequest request) {
        if (productoRepository.existsByCodigo(request.codigo())) {
            throw new IllegalArgumentException("Ya existe un producto con el código: " + request.codigo());
        }

        Categoria categoria = categoriaRepository.findById(request.categoriaId())
                .orElseThrow(() -> new RuntimeException("No se encontró la categoría con ID: " + request.categoriaId()));
        UnidadMedida unidad = unidadMedidaRepository.findById(request.unidadMedidaId())
                .orElseThrow(() -> new RuntimeException("No se encontró la unidad de medida con ID: " + request.unidadMedidaId()));
        Impuesto impuesto = impuestoRepository.findById(request.impuestoId())
                .orElseThrow(() -> new RuntimeException("No se encontró el impuesto con ID: " + request.impuestoId()));

        Producto producto = Producto.builder()
                .codigo(request.codigo().trim())
                .nombre(request.nombre().trim())
                .precioUnitario(request.precioUnitario())
                .categoria(categoria)
                .unidadMedida(unidad)
                .impuesto(impuesto)
                .build();

        producto.inicializarStock(request.stockInicial(), request.stockMinimo());

        return productoMapper.toResponse(productoRepository.save(producto));
    }

    /**
     * Obtiene la lista completa de todos los productos registrados.
     * @return Lista de productos.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponse> listarTodos() {
        return productoRepository.findAll().stream()
                .map(productoMapper::toResponse)
                .toList();
    }
    /**
     * Busca un producto por su identificador único ID.
     * @param id Identificador primario del producto.
     * @return Un Optional que contiene el producto si existe.
     */
    @Override
    @Transactional(readOnly = true)
    public ProductoResponse buscarPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el producto buscado por el ID: " + id));
        return productoMapper.toResponse(producto);
    }
    /**
     * Busca un producto por su código único de catálogo.
     * @param codigo Código alfanumérico del producto.
     * @return Un Optional que contiene el producto si existe.
     */
    @Override
    @Transactional(readOnly = true)
    public ProductoResponse buscarPorCodigo(String codigo) {
        Producto producto = productoRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RuntimeException("No se encontró el producto con código: " + codigo));
        return productoMapper.toResponse(producto);
    }
    /**
     * Actualiza la información, relaciones y stock de un producto existente.
     * @param id Identificador del producto a actualizar.
     * @param request Objeto con los nuevos datos a aplicar.
     * @return El producto actualizado y guardado.
     */
    @Override
    @Transactional
    public ProductoResponse actualizarProducto(Long id, ProductoRequest request) {
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el producto con ID: " + id));

        if (!productoExistente.getCodigo().equals(request.codigo())
                && productoRepository.existsByCodigo(request.codigo())) {
            throw new IllegalArgumentException("El código " + request.codigo() + " ya está en uso por otro producto.");
        }

        Categoria categoria = categoriaRepository.findById(request.categoriaId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        UnidadMedida unidadMedida = unidadMedidaRepository.findById(request.unidadMedidaId())
                .orElseThrow(() -> new RuntimeException("Unidad de medida no encontrada"));
        Impuesto impuesto = impuestoRepository.findById(request.impuestoId())
                .orElseThrow(() -> new RuntimeException("Impuesto no encontrado"));

        productoExistente.setCodigo(request.codigo().trim());
        productoExistente.setNombre(request.nombre().trim());
        productoExistente.setPrecioUnitario(request.precioUnitario());
        productoExistente.setCategoria(categoria);
        productoExistente.setUnidadMedida(unidadMedida);
        productoExistente.setImpuesto(impuesto);

        if (productoExistente.getStockInventario() != null) {
            if (request.stockInicial() != null) {
                productoExistente.getStockInventario().setCantidadDisponible(request.stockInicial());
            }
            if (request.stockMinimo() != null) {
                productoExistente.getStockInventario().setStockMinimo(request.stockMinimo());
            }
            productoExistente.getStockInventario().setUltimaActualizacion(LocalDateTime.now(ZoneId.of("America/Lima")));
        }

        return productoMapper.toResponse(productoRepository.save(productoExistente));
    }
    /**
     * Elimina un producto de la base de datos por su ID.
     * @param id Identificador único del producto a eliminar.
     */
    @Override
    @Transactional
    public void eliminarProducto(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el producto con ID: " + id));
        productoRepository.delete(producto);
    }
}