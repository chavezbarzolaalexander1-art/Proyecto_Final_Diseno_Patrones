package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.strategy;

import org.springframework.stereotype.Component;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model.MetodoPago;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model.Pago;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contexto del patrón Strategy: selecciona la estrategia según el MetodoPago del pago
 * y delega en ella la validación y el cálculo de la comisión.
 * Spring inyecta todas las implementaciones de EstrategiaPago, por lo que agregar un
 * nuevo medio de pago solo requiere crear una nueva estrategia.
 */
@Component
public class ProcesadorPago {

    private final Map<String, EstrategiaPago> estrategias = new HashMap<>();

    public ProcesadorPago(List<EstrategiaPago> disponibles) {
        disponibles.forEach(e -> estrategias.put(e.metodo(), e));
    }

    /** Indica si existe una estrategia para el medio de pago indicado. */
    public boolean soporta(String metodo) {
        return estrategias.containsKey(metodo);
    }

    public ResultadoPago procesar(Pago pago) {
        EstrategiaPago estrategia = obtenerEstrategia(pago.getMetodoPago());
        estrategia.validar(pago);
        double comision = estrategia.calcularComision(pago.getMonto());
        return new ResultadoPago(estrategia.metodo(), pago.getMonto(), comision, pago.getMonto() - comision);
    }

    private EstrategiaPago obtenerEstrategia(MetodoPago metodoPago) {
        if (metodoPago == null) {
            throw new IllegalArgumentException("El pago debe indicar un método de pago");
        }
        EstrategiaPago estrategia = estrategias.get(metodoPago.getNombre());
        if (estrategia == null) {
            throw new IllegalArgumentException("Método de pago no soportado: " + metodoPago.getNombre());
        }
        return estrategia;
    }
}
