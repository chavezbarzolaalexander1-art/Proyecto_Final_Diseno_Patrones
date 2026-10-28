package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.Categoria;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.Impuesto;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.Producto;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.UnidadMedida;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.repository.CategoriaRepository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.repository.ImpuestoRepository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.repository.ProductoRepository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.repository.UnidadMedidaRepository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.service.ProductoService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final UnidadMedidaRepository unidadMedidaRepository;
    private final ImpuestoRepository impuestoRepository;

    /**
     * Registra un nuevo producto en el sistema e inicializa su stock predeterminado si no fue especificado.
     * @param producto Objeto Producto con la información a registrar.
     * @return El producto guardado en la base de datos.
     */
    @Override
    @Transactional
    public Producto registrarProducto(Producto producto) {
        if (productoRepository.existsByCodigo(producto.getCodigo())) {
            throw new IllegalArgumentException("Ya existe un producto con el código: " + producto.getCodigo());
        }

        // Si el producto no trae stock inicializado, invocamos el helper de la entidad
        if (producto.getStockInventario() == null) {
            producto.inicializarStock(0, 10);
        } else {
            producto.getStockInventario().setProducto(producto);
        }

        return productoRepository.save(producto);
    }

    /**
     * Obtiene la lista completa de todos los productos registrados.
     * @return Lista de productos.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    /**
     * Busca un producto por su identificador único ID.
     * @param id Identificador primario del producto.
     * @return Un Optional que contiene el producto si existe.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> buscarPorId(Long id) {
        return productoRepository.findById(id);
    }

    /**
     * Busca un producto por su código único de catálogo.
     * @param codigo Código alfanumérico del producto.
     * @return Un Optional que contiene el producto si existe.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> buscarPorCodigo(String codigo) {
        return productoRepository.findByCodigo(codigo);
    }

    /**
     * Actualiza la información, relaciones y stock de un producto existente.
     * @param id Identificador del producto a actualizar.
     * @param productoDetalles Objeto con los nuevos datos a aplicar.
     * @return El producto actualizado y guardado.
     */
    @Override
    @Transactional
    public Producto actualizarProducto(Long id, Producto productoDetalles) {
        // 1. Buscar si el producto existe
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el producto con ID: " + id));

        // 2. Validar que el nuevo código no entre en conflicto con otro producto registrado
        if (!productoExistente.getCodigo().equals(productoDetalles.getCodigo()) &&
                productoRepository.existsByCodigo(productoDetalles.getCodigo())) {
            throw new IllegalArgumentException("El código " + productoDetalles.getCodigo() + " ya está en uso por otro producto.");
        }

        // 3. Validar las relaciones enviadas en el objeto
        Categoria categoria = categoriaRepository.findById(productoDetalles.getCategoria().getId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        UnidadMedida unidadMedida = unidadMedidaRepository.findById(productoDetalles.getUnidadMedida().getId())
                .orElseThrow(() -> new RuntimeException("Unidad de medida no encontrada"));
        Impuesto impuesto = impuestoRepository.findById(productoDetalles.getImpuesto().getId())
                .orElseThrow(() -> new RuntimeException("Impuesto no encontrado"));

        // 4. Actualizar los valores del producto
        productoExistente.setCodigo(productoDetalles.getCodigo());
        productoExistente.setNombre(productoDetalles.getNombre());
        productoExistente.setPrecioUnitario(productoDetalles.getPrecioUnitario());
        productoExistente.setCategoria(categoria);
        productoExistente.setUnidadMedida(unidadMedida);
        productoExistente.setImpuesto(impuesto);

        // 5. Actualizar stock si viene información en el payload
        if (productoDetalles.getStockInventario() != null && productoExistente.getStockInventario() != null) {
            productoExistente.getStockInventario().setCantidadDisponible(
                    productoDetalles.getStockInventario().getCantidadDisponible()
            );
            productoExistente.getStockInventario().setStockMinimo(
                    productoDetalles.getStockInventario().getStockMinimo()
            );
        }

        return productoRepository.save(productoExistente);
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

        // Gracias a la relación CascadeType.ALL en Producto, al eliminar el producto se elimina su StockInventario automáticamente
        productoRepository.delete(producto);
    }
}