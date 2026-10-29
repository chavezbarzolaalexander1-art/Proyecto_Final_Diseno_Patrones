package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "boleta")
@Getter
@Setter
@NoArgsConstructor
public class Boleta extends ComprobantePago {

    @Column(length = 100)
    private String observaciones;
}
