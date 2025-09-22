package pe.edu.upeu.exa1lp2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmpresaDTO {
    private Long id;
    private String nombre;
    private String direccion;
    private String ruc;
}
