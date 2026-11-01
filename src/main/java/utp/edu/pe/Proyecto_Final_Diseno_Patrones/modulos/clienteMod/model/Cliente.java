package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String razonSocialONombre;

    @Column(nullable = false, unique = true, length = 20)
    private String numeroDocumento;

    @Column(length = 100)
    private String email;

    @Column(length = 15)
    private String telefono;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_documento", nullable = false, length = 20)
    private TipoDocumento tipoDocumento;

    @Embedded
    private DireccionCliente direccion;
}