package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model;
import jakarta.persistence.*;
import lombok.*;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.ComprobantePago;

import java.time.LocalDateTime;

@Entity
@Table(name = "anulacion_comprobante")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnulacionComprobante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String motivo;

    @Column(nullable = false)
    private LocalDateTime fechaAnulacion;

    @OneToOne
    @JoinColumn(name = "comprobante_id", nullable = false, unique = true)
    private ComprobantePago comprobantePago;
}