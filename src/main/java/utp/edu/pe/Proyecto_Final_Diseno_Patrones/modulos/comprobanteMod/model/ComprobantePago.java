package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model.Cliente;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model.Vendedor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "comprobante_pago",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_comprobante_serie_numero",
                columnNames = {"serie", "numero"}
        )
)
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor

public abstract class ComprobantePago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 4)
    private String serie;

    @Column(nullable = false, length = 8)
    private String numero;

    @Column(nullable = false)
    private LocalDateTime fechaEmision;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal subtotal;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal igv;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Vendedor vendedor;

    @ManyToOne
    @JoinColumn(name = "estado_id", nullable = false)
    private EstadoComprobante estado;

    @OneToMany(
            mappedBy = "comprobantePago",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<DetalleComprobante> detalles = new ArrayList<>();

    public void agregarDetalle(DetalleComprobante detalle) {
        detalles.add(detalle);
        detalle.setComprobantePago(this);
    }
}

