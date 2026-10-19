package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "factura")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Factura extends ComprobantePago {

    @Column(length = 20)
    private String ordenCompra;

    @Column(length = 200)
    private String guiaRemision;
}