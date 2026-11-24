package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.reporteComprobanteMod.service.impl;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.model.Empresa;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.repository.EmpresaRepository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.model.ComprobantePago;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.comprobanteMod.repository.ComprobantePagoRepository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.reporteComprobanteMod.adapter.GeneradorReporteComprobante;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.reporteComprobanteMod.dto.ArchivoReporte;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.reporteComprobanteMod.service.ReporteComprobanteService;

/**
 * Implementación del servicio {@link ReporteComprobanteService} que orquesta la generación
 * de reportes de comprobantes de pago delegando en el adaptador correspondiente según el formato.
 */
@Service
public class ReporteComprobanteServiceImpl implements ReporteComprobanteService {

  private final ComprobantePagoRepository comprobanteRepository;
  private final EmpresaRepository empresaRepository;
  private final Map<String, GeneradorReporteComprobante> adaptadores;

  /**
   * Construye el servicio indexando dinámicamente todos los adaptadores de reporte disponibles
   * por su formato en mayúsculas.
   *
   * @param comprobanteRepository repositorio para consultar los comprobantes de pago
   * @param empresaRepository repositorio para obtener los datos de la empresa emisora
   * @param listaAdaptadores lista de implementaciones de {@link GeneradorReporteComprobante}
   *     inyectadas por Spring
   */
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
    String claveFormato = (formato == null || formato.isBlank()) ? "PDF" :
        formato.trim().toUpperCase();
    GeneradorReporteComprobante adaptador = adaptadores.get(claveFormato);

    if (adaptador == null) {
      throw new IllegalArgumentException("Formato de reporte no soportado: "
          + formato
          +
          ". Formatos disponibles: " + adaptadores.keySet());
    }

    ComprobantePago comprobante = comprobanteRepository.findById(comprobanteId)
        .orElseThrow(() -> new RuntimeException("No se encontró el comprobante con ID: "
            + comprobanteId));

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