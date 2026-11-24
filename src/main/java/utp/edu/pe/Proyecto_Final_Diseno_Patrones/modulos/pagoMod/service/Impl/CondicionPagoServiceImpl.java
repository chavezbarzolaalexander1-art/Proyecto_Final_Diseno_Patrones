package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.service.Impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.model.CondicionPago;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.repository.CondicionPagoRepository;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.pagoMod.service.CondicionPagoService;

/**
 * Implementación del servicio encargado de gestionar las condiciones de pago.
 */
@Service
@RequiredArgsConstructor
public class CondicionPagoServiceImpl implements CondicionPagoService {

  private final CondicionPagoRepository condicionPagoRepository;

  @Override
  @Transactional
  public CondicionPago buscarOcrear(String descripcion) {
    String descLimpia = descripcion.trim().toUpperCase();
    return condicionPagoRepository.findByDescripcion(descLimpia)
        .orElseGet(() -> condicionPagoRepository.save(
            CondicionPago.builder().descripcion(descLimpia).build()
        ));
  }

  @Override
  @Transactional(readOnly = true)
  public List<CondicionPago> listarTodas() {
    return condicionPagoRepository.findAll();
  }
}
