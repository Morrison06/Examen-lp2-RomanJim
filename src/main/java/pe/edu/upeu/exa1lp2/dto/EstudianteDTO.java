package pe.edu.upeu.exa1lp2.dto;

import lombok.Data;

@Data
public class EstudianteDTO {
    private Long id;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String telefono;
}