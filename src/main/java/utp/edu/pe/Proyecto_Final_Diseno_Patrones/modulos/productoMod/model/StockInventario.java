package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "stock_inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer cantidadDisponible;

    @Column(nullable = false)
    private Integer stockMinimo;

    private LocalDateTime ultimaActualizacion;

    @OneToOne
    @JoinColumn(name = "producto_id", nullable = false, unique = true)
    private Producto producto;
}