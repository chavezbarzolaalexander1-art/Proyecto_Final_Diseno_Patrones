package utp.edu.pe.Proyecto_Final_Diseno_Patrones.modulos.clienteMod.dto;

public record VendedorRequest(
        String nombres,
        String apellidos,
        String dni,
        String codigoVendedor
) {
}
