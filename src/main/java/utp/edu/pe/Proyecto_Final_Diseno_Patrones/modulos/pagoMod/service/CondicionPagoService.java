package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.service;

import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model.CondicionPago;

import java.util.List;

public interface CondicionPagoService {

    CondicionPago obtnerOCrear(String descripcion);

    List<CondicionPago> listarTodas();
}
