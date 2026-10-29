package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.factory;

import org.springframework.stereotype.Component;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.dto.CrearComprobanteRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.Boleta;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.ComprobantePago;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.Factura;

@Component
public class ComprobanteFactory {

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
