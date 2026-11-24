package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.state;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.EstadoComprobante;

/**
 * Contexto del patrón State encargado de resolver y proveer la implementación de estado
 * correspondiente a un {@link EstadoComprobante}.
 */
@Component
@RequiredArgsConstructor
public class ContextoEstadoComprobante {
  private final EstadoEmitidoState estadoEmitidoState;
  private final EstadoAnuladoState estadoAnuladoState;

  /**
   * Obtiene la instancia del estado de comportamiento asociada al estado del comprobante indicado.
   *
   * @param estado estado enumerado del comprobante de pago
   * @return implementación de {@link EstadoComprobanteState} correspondiente
   */
  public EstadoComprobanteState obtenerEstado(EstadoComprobante estado) {
    return switch (estado) {
      case EMITIDO -> estadoEmitidoState;
      case ANULADO -> estadoAnuladoState;
    };
  }
}
