package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto;

import lombok.Data;

@Data
public class ClienteDto {
    private Long id;
    private String nombre;
    private String apellido;
    private String numeroDocumento;
    private String email;
    private String telefono;
}