package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estado_comprobante")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstadoComprobante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String nombre; // EMITIDO, PAGADO, ANULADO
}
