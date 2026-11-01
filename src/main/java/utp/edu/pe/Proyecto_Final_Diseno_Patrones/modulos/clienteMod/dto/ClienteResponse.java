package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model.TipoDocumento;

public record ClienteResponse (
        Long id,
        String razonSocialONombre,
        String numeroDocumento,
        String email,
        String telefono,
        TipoDocumento tipoDocumento,
        DireccionDto direccion
){
}
