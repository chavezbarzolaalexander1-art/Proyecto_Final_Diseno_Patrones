package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model;
import jakarta.persistence.*;
import lombok.*;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.ComprobantePago;

import java.time.LocalDateTime;

@Entity
@Table(name = "pago")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double monto;

    @Column(nullable = false)
    private LocalDateTime fechaPago;

    @Column(length = 100)
    private String numeroTransaccion;

    @ManyToOne
    @JoinColumn(name = "comprobante_id", nullable = false)
    private ComprobantePago comprobantePago;

    @ManyToOne
    @JoinColumn(name = "metodo_pago_id", nullable = false)
    private MetodoPago metodoPago;
}