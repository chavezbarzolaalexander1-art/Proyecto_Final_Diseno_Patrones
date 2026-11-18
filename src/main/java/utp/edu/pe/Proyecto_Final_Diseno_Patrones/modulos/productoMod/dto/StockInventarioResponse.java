package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto;

import java.time.LocalDateTime;

public record StockInventarioResponse(
        Long id,
        Long productoId,
        String codigoProducto,
        String nombreProducto,
        Integer cantidadDisponible,
        Integer stockMinimo,
        LocalDateTime ultimaActualizacion
) {
}