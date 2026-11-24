package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad JPA que representa el registro de inventario y control de existencias de un producto.
 */
@Entity
@Table(name = "stock_inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockInventario {

  @Id
  private Long id;

  @Column(nullable = false)
  private Integer cantidadDisponible;

  @Column(nullable = false)
  private Integer stockMinimo;

  private LocalDateTime ultimaActualizacion;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  @JoinColumn(name = "producto_id", nullable = false)
  @JsonIgnore
  private Producto producto;
}