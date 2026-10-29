package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.service.Impl;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model.Cliente;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model.Vendedor;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.dto.*;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.factory.ComprobanteFactory;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.*;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.repository.AnulacionComprobanteRepository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.repository.ComprobantePagoRepository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.repository.EstadoComprobanteRepository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.service.ComprobanteService;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model.AnulacionComprobante;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model.Producto;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.repository.ProductoRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComprobanteServiceImpl implements ComprobanteService {

    private static final String ESTADO_EMITIDO = "EMITIDO";
    private static final String ESTADO_ANULADO = "ANULADO";

    private final ComprobantePagoRepository comprobanteRepository;
    private final EstadoComprobanteRepository estadoRepository;
    private final AnulacionComprobanteRepository anulacionRepository;
    private final ProductoRepository productoRepository;
    private final ComprobanteFactory comprobanteFactory;
    private final EntityManager entityManager;

    @Override
    @Transactional
    public ComprobanteResponse emitir(CrearComprobanteRequest request) {
        String serie = request.serie().trim().toUpperCase();
        String numero = completarNumero(request.numero());

        if (comprobanteRepository.existsBySerieAndNumero(serie, numero)) {
            throw new IllegalArgumentException(
                    "Ya existe el comprobante " + serie + "-" + numero
            );
        }

        Cliente cliente = buscarEntidad(Cliente.class, request.clienteId(), "cliente");
        Vendedor vendedor = buscarEntidad(Vendedor.class, request.vendedorId(), "vendedor");
        EstadoComprobante estadoEmitido = obtenerOCrearEstado(ESTADO_EMITIDO);

        ComprobantePago comprobante = comprobanteFactory.crear(request);
        comprobante.setSerie(serie);
        comprobante.setNumero(numero);
        comprobante.setFechaEmision(LocalDateTime.now());
        comprobante.setCliente(cliente);
        comprobante.setVendedor(vendedor);
        comprobante.setEstado(estadoEmitido);

        BigDecimal subtotalGeneral = BigDecimal.ZERO;
        BigDecimal igvGeneral = BigDecimal.ZERO;

        for (DetalleComprobanteRequest detalleRequest : request.detalles()) {
            Producto producto = productoRepository.findById(detalleRequest.productoId())
                    .orElseThrow(() -> new RuntimeException(
                            "No se encontró el producto con ID: " + detalleRequest.productoId()
                    ));

            if (producto.getPrecioUnitario() == null || producto.getPrecioUnitario() < 0) {
                throw new IllegalArgumentException(
                        "El producto " + producto.getCodigo() + " no tiene un precio válido"
                );
            }

            BigDecimal precioUnitario = BigDecimal.valueOf(producto.getPrecioUnitario())
                    .setScale(2, RoundingMode.HALF_UP);
            BigDecimal subtotalDetalle = precioUnitario
                    .multiply(BigDecimal.valueOf(detalleRequest.cantidad()))
                    .setScale(2, RoundingMode.HALF_UP);

            BigDecimal porcentajeImpuesto = producto.getImpuesto() == null
                    || producto.getImpuesto().getPorcentaje() == null
                    ? BigDecimal.ZERO
                    : BigDecimal.valueOf(producto.getImpuesto().getPorcentaje());
            BigDecimal igvDetalle = subtotalDetalle
                    .multiply(porcentajeImpuesto)
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

            DetalleComprobante detalle = DetalleComprobante.builder()
                    .producto(producto)
                    .cantidad(detalleRequest.cantidad())
                    .precioUnitario(precioUnitario)
                    .subtotal(subtotalDetalle)
                    .build();
            comprobante.agregarDetalle(detalle);

            subtotalGeneral = subtotalGeneral.add(subtotalDetalle);
            igvGeneral = igvGeneral.add(igvDetalle);
        }

        comprobante.setSubtotal(subtotalGeneral.setScale(2, RoundingMode.HALF_UP));
        comprobante.setIgv(igvGeneral.setScale(2, RoundingMode.HALF_UP));
        comprobante.setTotal(
                subtotalGeneral.add(igvGeneral).setScale(2, RoundingMode.HALF_UP)
        );

        return convertirRespuesta(comprobanteRepository.save(comprobante));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ComprobanteResponse> listarTodos() {
        return comprobanteRepository.findAll().stream()
                .map(this::convertirRespuesta)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ComprobanteResponse buscarPorId(Long id) {
        return convertirRespuesta(obtenerComprobante(id));
    }

    @Override
    @Transactional(readOnly = true)
    public ComprobanteResponse buscarPorSerieYNumero(String serie, String numero) {
        ComprobantePago comprobante = comprobanteRepository
                .findBySerieAndNumero(
                        serie.trim().toUpperCase(),
                        completarNumero(numero)
                )
                .orElseThrow(() -> new RuntimeException("No se encontró el comprobante solicitado"));
        return convertirRespuesta(comprobante);
    }

    @Override
    @Transactional
    public ComprobanteResponse anular(Long id, String motivo) {
        if (motivo == null || motivo.isBlank()) {
            throw new IllegalArgumentException("El motivo de anulación es obligatorio");
        }
        if (motivo.trim().length() > 255) {
            throw new IllegalArgumentException("El motivo no puede superar 255 caracteres");
        }

        ComprobantePago comprobante = obtenerComprobante(id);
        if (ESTADO_ANULADO.equalsIgnoreCase(comprobante.getEstado().getNombre())
                || anulacionRepository.existsByComprobantePagoId(id)) {
            throw new IllegalArgumentException("El comprobante ya se encuentra anulado");
        }

        comprobante.setEstado(obtenerOCrearEstado(ESTADO_ANULADO));
        AnulacionComprobante anulacion = AnulacionComprobante.builder()
                .motivo(motivo.trim())
                .fechaAnulacion(LocalDateTime.now())
                .comprobantePago(comprobante)
                .build();
        anulacionRepository.save(anulacion);

        return convertirRespuesta(comprobanteRepository.save(comprobante));
    }

    private ComprobantePago obtenerComprobante(Long id) {
        return comprobanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "No se encontró el comprobante con ID: " + id
                ));
    }

    private EstadoComprobante obtenerOCrearEstado(String nombre) {
        return estadoRepository.findByNombreIgnoreCase(nombre)
                .orElseGet(() -> estadoRepository.save(
                        EstadoComprobante.builder().nombre(nombre).build()
                ));
    }

    private <T> T buscarEntidad(Class<T> tipo, Long id, String nombre) {
        T entidad = entityManager.find(tipo, id);
        if (entidad == null) {
            throw new RuntimeException(
                    "No se encontró el " + nombre + " con ID: " + id
            );
        }
        return entidad;
    }

    private String completarNumero(String numero) {
        return String.format("%08d", Long.parseLong(numero.trim()));
    }

    private ComprobanteResponse convertirRespuesta(ComprobantePago comprobante) {
        List<DetalleComprobanteResponse> detalles = comprobante.getDetalles().stream()
                .map(detalle -> new DetalleComprobanteResponse(
                        detalle.getId(),
                        detalle.getProducto().getId(),
                        detalle.getProducto().getCodigo(),
                        detalle.getProducto().getNombre(),
                        detalle.getCantidad(),
                        detalle.getPrecioUnitario(),
                        detalle.getSubtotal()
                ))
                .toList();

        TipoComprobante tipo;
        String observaciones = null;
        String ordenCompra = null;
        String guiaRemision = null;

        if (comprobante instanceof Boleta boleta) {
            tipo = TipoComprobante.BOLETA;
            observaciones = boleta.getObservaciones();
        } else if (comprobante instanceof Factura factura) {
            tipo = TipoComprobante.FACTURA;
            ordenCompra = factura.getOrdenCompra();
            guiaRemision = factura.getGuiaRemision();
        } else {
            throw new IllegalStateException("Tipo de comprobante no soportado");
        }

        return new ComprobanteResponse(
                comprobante.getId(),
                tipo,
                comprobante.getSerie(),
                comprobante.getNumero(),
                comprobante.getFechaEmision(),
                comprobante.getSubtotal(),
                comprobante.getIgv(),
                comprobante.getTotal(),
                comprobante.getCliente().getId(),
                comprobante.getCliente().getRazonSocialONombre(),
                comprobante.getVendedor().getId(),
                comprobante.getVendedor().getNombres() + " "
                        + comprobante.getVendedor().getApellidos(),
                comprobante.getEstado().getNombre(),
                observaciones,
                ordenCompra,
                guiaRemision,
                detalles
        );
    }
}
