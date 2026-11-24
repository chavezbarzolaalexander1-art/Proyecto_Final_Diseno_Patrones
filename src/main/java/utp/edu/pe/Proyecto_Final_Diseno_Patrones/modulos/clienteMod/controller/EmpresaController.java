package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.EmpresaRequest;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto.EmpresaResponse;
import utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.service.EmpresaService;

/**
 * Controlador REST para gestionar las operaciones CRUD de empresas.
 */
@RestController
@RequestMapping("/api/empresas")
@RequiredArgsConstructor
public class EmpresaController {
  private final EmpresaService empresaService;

  /**
   * Obtiene el listado completo de empresas registradas en el sistema.
   *
   * @return respuesta HTTP 200 (OK) con la lista de {@link EmpresaResponse}
   */
  @GetMapping
  public ResponseEntity<List<EmpresaResponse>> listar() {
    return ResponseEntity.ok(empresaService.listarEmpresas());
  }

  /**
   * Busca una empresa específica a partir de su identificador único.
   *
   * @param id identificador único de la empresa
   * @return respuesta HTTP 200 (OK) con los datos de la {@link EmpresaResponse} encontrada
   */
  @GetMapping("/{id}")
  public ResponseEntity<EmpresaResponse> buscarPorId(@PathVariable Long id) {
    return ResponseEntity.ok(empresaService.buscarPorId(id));
  }

  /**
   * Registra una nueva empresa en el sistema.
   *
   * @param request datos de entrada de la empresa a registrar
   * @return respuesta HTTP 201 (Created) con la {@link EmpresaResponse} creada
   */
  @PostMapping
  public ResponseEntity<EmpresaResponse> guardar(@RequestBody EmpresaRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(empresaService.guardarEmpresa(request));
  }

  /**
   * Actualiza los datos de una empresa existente.
   *
   * @param id identificador único de la empresa a actualizar
   * @param request nuevos datos de la empresa
   * @return respuesta HTTP 200 (OK) con la {@link EmpresaResponse} actualizada
   */
  @PutMapping("/{id}")
  public ResponseEntity<EmpresaResponse> actualizar(@PathVariable Long id,
                                                    @RequestBody EmpresaRequest request) {
    return ResponseEntity.ok(empresaService.actualizarEmpresa(id, request));
  }

  /**
   * Elimina una empresa del sistema a partir de su identificador.
   *
   * @param id identificador único de la empresa a eliminar
   * @return respuesta HTTP 204 (No Content) si la eliminación fue exitosa
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminar(@PathVariable Long id) {
    empresaService.eliminarEmpresa(id);
    return ResponseEntity.noContent().build();
  }
}
