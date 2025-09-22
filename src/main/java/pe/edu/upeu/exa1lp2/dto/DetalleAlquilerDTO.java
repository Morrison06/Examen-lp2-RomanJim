package pe.edu.upeu.exa1lp2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upeu.exa1lp2.entity.Alquiler;
import pe.edu.upeu.exa1lp2.entity.Equipo;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetalleAlquilerDTO {
    private Long id;
    private Integer cantidad;
    private BigDecimal precio;
    private Alquiler alquiler;  // Entidad directa
    private Equipo equipo;      // Entidad directa
}
