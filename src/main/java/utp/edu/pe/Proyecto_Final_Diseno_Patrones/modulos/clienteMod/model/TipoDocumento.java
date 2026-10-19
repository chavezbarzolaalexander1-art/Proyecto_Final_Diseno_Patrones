package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipo_documento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoDocumento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 10)
    private String codigo; // DNI, RUC, CE, PASAPORTE

    @Column(nullable = false, length = 50)
    private String descripcion;
}
