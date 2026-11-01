package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto;

public record EmpresaResponse(
        Long id,
        String ruc,
        String razonSocial,
        String direccionFiscal,
        String telefono
) {
}
