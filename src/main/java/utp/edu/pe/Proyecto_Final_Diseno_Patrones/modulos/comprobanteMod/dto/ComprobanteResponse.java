package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.dto;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.TipoComprobante;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model.CondicionPago;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
public record ComprobanteResponse(
        Long id,
        TipoComprobante tipo,
        String serie,
        String numero,
        LocalDateTime fechaEmision,
        BigDecimal subtotal,
        BigDecimal igv,
        BigDecimal total,
        Long clienteId,
        String cliente,
        Long vendedorId,
        String vendedor,
        String estadoComprobante,
        String estadoPago,
        CondicionPago condicionPago,
        String observaciones,
        String ordenCompra,
        String guiaRemision,
        List<DetalleComprobanteResponse> detalles
) {


}
