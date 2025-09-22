package pe.edu.upeu.exa1lp2.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipoDTO {
    private Long id;
    private String descripcion;
    private Integer cantidad;
    private BigDecimal precio;
}
