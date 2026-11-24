package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.factory;

import org.springframework.stereotype.Component;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.dto.CrearComprobanteRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.Boleta;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.ComprobantePago;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.Factura;

/**
 * Fábrica encargada de instanciar el tipo de comprobante
 * de pago correspondiente según la solicitud.
 */
@Component
public class ComprobanteFactory {

  /**
   * Crea una instancia de {@link ComprobantePago} de acuerdo con el tipo especificado.
   *
   * @param request datos de la solicitud para la creación del comprobante
   * @return instancia de {@link ComprobantePago} configurada como boleta o factura
   */
  public ComprobantePago crear(CrearComprobanteRequest request) {
    return switch (request.tipo()) {
      case BOLETA -> crearBoleta(request);
      case FACTURA -> crearFactura(request);
    };
  }

  private Boleta crearBoleta(CrearComprobanteRequest request) {
    Boleta boleta = new Boleta();
    boleta.setObservaciones(request.observaciones());
    return boleta;
  }

  private Factura crearFactura(CrearComprobanteRequest request) {
    Factura factura = new Factura();
    factura.setOrdenCompra(request.ordenCompra());
    factura.setGuiaRemision(request.guiaRemision());
    return factura;
  }
}
