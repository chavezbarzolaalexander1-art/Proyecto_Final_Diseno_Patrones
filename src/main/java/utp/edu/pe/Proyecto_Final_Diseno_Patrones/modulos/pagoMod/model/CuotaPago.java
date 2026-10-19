package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model;
import jakarta.persistence.*;
import lombok.*;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.ComprobantePago;

import java.time.LocalDate;

@Entity
@Table(name = "cuota_pago")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CuotaPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer numeroCuota;

    @Column(nullable = false)
    private Double monto;

    @Column(nullable = false)
    private LocalDate fechaVencimiento;

    @Column(nullable = false)
    private Boolean pagado;

    @ManyToOne
    @JoinColumn(name = "comprobante_id", nullable = false)
    private ComprobantePago comprobantePago;
}