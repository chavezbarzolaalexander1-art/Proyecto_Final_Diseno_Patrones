package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.state;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.EstadoComprobante;

@Component
@RequiredArgsConstructor
public class ContextoEstadoComprobante {
    private final EstadoEmitidoState estadoEmitidoState;
    private final EstadoAnuladoState estadoAnuladoState;

    public EstadoComprobanteState obtenerEstado(EstadoComprobante estado) {
        return switch (estado) {
            case EMITIDO -> estadoEmitidoState;
            case ANULADO -> estadoAnuladoState;
        };
    }
}
