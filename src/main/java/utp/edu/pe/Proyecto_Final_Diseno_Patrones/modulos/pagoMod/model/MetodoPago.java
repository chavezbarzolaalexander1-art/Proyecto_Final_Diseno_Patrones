package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "metodo_pago")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MetodoPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String nombre; // EFECTIVO, TARJETA, TRANSFERENCIA, YAPE/PLIN
}