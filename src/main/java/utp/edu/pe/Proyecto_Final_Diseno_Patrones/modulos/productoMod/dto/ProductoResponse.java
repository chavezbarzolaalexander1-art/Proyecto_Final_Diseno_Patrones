package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.dto;

public record ProductoResponse(
        Long id,
        String codigo,
        String nombre,
        Double precioUnitario,
        Long categoriaId,
        String categoriaNombre,
        Long unidadMedidaId,
        String unidadMedidaCodigo,
        String unidadMedidaDescripcion,
        Long impuestoId,
        String impuestoNombre,
        Double impuestoPorcentaje,
        Integer cantidadDisponible,
        Integer stockMinimo
) {
}