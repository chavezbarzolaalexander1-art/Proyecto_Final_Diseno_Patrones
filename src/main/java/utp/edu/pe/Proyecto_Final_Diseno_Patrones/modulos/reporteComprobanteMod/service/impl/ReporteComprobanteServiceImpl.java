package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.reporteComprobanteMod.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model.Empresa;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.repository.EmpresaRepository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.ComprobantePago;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.repository.ComprobantePagoRepository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.reporteComprobanteMod.adapter.GeneradorReporteComprobante;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.reporteComprobanteMod.dto.ArchivoReporte;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.reporteComprobanteMod.service.ReporteComprobanteService;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ReporteComprobanteServiceImpl implements ReporteComprobanteService {

    private final ComprobantePagoRepository comprobanteRepository;
    private final EmpresaRepository empresaRepository;
    private final Map<String, GeneradorReporteComprobante> adaptadores;

    public ReporteComprobanteServiceImpl(
            ComprobantePagoRepository comprobanteRepository,
            EmpresaRepository empresaRepository,
            List<GeneradorReporteComprobante> listaAdaptadores
    ) {
        this.comprobanteRepository = comprobanteRepository;
        this.empresaRepository = empresaRepository;
        this.adaptadores = listaAdaptadores.stream()
                .collect(Collectors.toMap(
                        a -> a.formato().toUpperCase(),
                        Function.identity()
                ));
    }
    @Override
    @Transactional(readOnly = true)
    public ArchivoReporte exportarComprobante(Long comprobanteId, String formato) {
        String claveFormato = (formato == null || formato.isBlank()) ? "PDF" : formato.trim().toUpperCase();
        GeneradorReporteComprobante adaptador = adaptadores.get(claveFormato);

        if (adaptador == null) {
            throw new IllegalArgumentException("Formato de reporte no soportado: " + formato +
                    ". Formatos disponibles: " + adaptadores.keySet());
        }

        ComprobantePago comprobante = comprobanteRepository.findById(comprobanteId)
                .orElseThrow(() -> new RuntimeException("No se encontró el comprobante con ID: " + comprobanteId));

        // Obtiene la primera empresa registrada como emisora (si existe en BD)
        Empresa empresaEmisora = empresaRepository.findAll().stream().findFirst().orElse(null);

        byte[] contenido = adaptador.generar(comprobante, empresaEmisora);
        String nombreArchivo = String.format("%s-%s-%s.%s",
                comprobante.getTipoComprobante().name(),
                comprobante.getSerie(),
                comprobante.getNumero(),
                adaptador.extension());

        return new ArchivoReporte(nombreArchivo, adaptador.contentType(), contenido);
    }
}