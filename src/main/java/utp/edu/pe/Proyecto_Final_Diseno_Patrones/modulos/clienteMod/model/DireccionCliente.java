package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DireccionCliente {

    @Column(nullable = false, length = 200)
    private String direccion;

    @Column(length = 50)
    private String departamento;

    @Column(length = 50)
    private String provincia;

    @Column(length = 50)
    private String distrito;

}