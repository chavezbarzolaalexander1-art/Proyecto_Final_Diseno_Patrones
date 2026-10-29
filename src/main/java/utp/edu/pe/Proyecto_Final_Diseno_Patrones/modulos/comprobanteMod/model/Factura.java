package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "factura")
@Getter
@Setter
@NoArgsConstructor
public class Factura extends ComprobantePago {

    @Column(length = 20)
    private String ordenCompra;

    @Column(length = 200)
    private String guiaRemision;
}
