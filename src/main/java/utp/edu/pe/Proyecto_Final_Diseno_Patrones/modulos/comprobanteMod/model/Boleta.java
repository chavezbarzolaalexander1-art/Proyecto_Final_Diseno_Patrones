package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "boleta")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Boleta extends ComprobantePago {

    @Column(length = 100)
    private String observaciones;
}