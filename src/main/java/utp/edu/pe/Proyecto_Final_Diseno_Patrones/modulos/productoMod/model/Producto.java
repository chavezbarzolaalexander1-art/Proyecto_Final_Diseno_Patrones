package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.productoMod.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad JPA que representa un producto comercializable dentro del sistema,
 * incluyendo su precio, clasificación, unidad de medida, impuesto aplicable y control de stock.
 */
@Entity
@Table(name = "producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 30)
  private String codigo;

  @Column(nullable = false, length = 150)
  private String nombre;

  @Column(nullable = false)
  private Double precioUnitario;

  @ManyToOne
  @JoinColumn(name = "categoria_id", nullable = false)
  private Categoria categoria;

  @ManyToOne
  @JoinColumn(name = "unidad_medida_id", nullable = false)
  private UnidadMedida unidadMedida;

  @ManyToOne
  @JoinColumn(name = "impuesto_id", nullable = false)
  private Impuesto impuesto;

  @OneToOne(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private StockInventario stockInventario;

  /**
   * Inicializa el registro de inventario del producto con valores predeterminados en caso de que
   * los parámetros proporcionados sean nulos.
   *
   * @param cantidadInicial cantidad inicial disponible en stock (por defecto 0)
   * @param stockMinimo umbral mínimo permitido.
   */
  public void inicializarStock(Integer cantidadInicial, Integer stockMinimo) {
    StockInventario stock = StockInventario.builder()
        .cantidadDisponible(cantidadInicial != null ? cantidadInicial : 0)
        .stockMinimo(stockMinimo != null ? stockMinimo : 10)
        .ultimaActualizacion(LocalDateTime.now(ZoneId.of("America/Lima")))
        .producto(this)
        .build();
    this.stockInventario = stock;
  }
}