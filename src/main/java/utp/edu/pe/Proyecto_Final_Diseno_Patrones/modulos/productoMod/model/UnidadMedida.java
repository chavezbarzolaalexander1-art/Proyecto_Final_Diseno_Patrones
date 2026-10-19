package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "unidad_medida")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnidadMedida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 10)
    private String codigo; // NIU (Unidades), ZZ (Servicios), KG, etc.

    @Column(nullable = false, length = 50)
    private String descripcion;
}