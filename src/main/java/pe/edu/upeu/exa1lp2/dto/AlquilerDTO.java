package pe.edu.upeu.exa1lp2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upeu.exa1lp2.entity.Empresa;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlquilerDTO {
    private Long id;
    private LocalDate fechaSalida;
    private LocalDate fechaEntrada;
    private String observacion;
    private EmpresaDTO empresa;
}