package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.service;

import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.dto.AnulacionRequest;

public interface AnulacionComprobanteService {
    void anularComprobante(Long comprobanteId, AnulacionRequest request);
}
