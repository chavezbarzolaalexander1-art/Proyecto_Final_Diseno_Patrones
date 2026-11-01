package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.state;

import org.springframework.stereotype.Component;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.ComprobantePago;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.EstadoComprobante;

import java.math.BigDecimal;
@Component
public class EstadoEmitidoState implements  EstadoComprobanteState{
    @Override
    public void validarPermitePago(ComprobantePago comprobante) {
        // Un comprobante en estado EMITIDO permite recibir pagos.
    }

    @Override
    public void evaluarSiguienteEstadoTrasPago(ComprobantePago comprobante, double totalPagadoAcumulado) {
        BigDecimal pagado = BigDecimal.valueOf(totalPagadoAcumulado);
        if (pagado.compareTo(comprobante.getTotal()) >= 0) {
            comprobante.setEstado(EstadoComprobante.PAGADO);
        }
    }

    @Override
    public void validarPermiteAnulacion(ComprobantePago comprobante) {
        // Permitido anular si está recien emitido.
    }
}
