package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

@Entity
@Table(name = "boleta")
@Getter
@Setter
@NoArgsConstructor
public class Boleta extends ComprobantePago {

    @Column(length = 100)
    private String observaciones;

    @Override
    public TipoComprobante getTipoComprobante() {
        return TipoComprobante.BOLETA;
    }

    @Override
    public String getTituloDocumento() {
        return "BOLETA DE VENTA ELECTRÓNICA";
    }

    @Override
    public Map<String, String> getDatosAdicionalesDocumento() {
        Map<String, String> datos = new LinkedHashMap<>();
        if (observaciones != null && !observaciones.isBlank()) {
            datos.put("Observaciones", observaciones);
        }
        return datos;
    }
}
