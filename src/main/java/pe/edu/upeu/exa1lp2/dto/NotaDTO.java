package pe.edu.upeu.exa1lp2.dto;

import lombok.Data;

@Data
public class NotaDTO {
    private Long id;
    private Double nota;
    private EstudianteDTO estudiante; // Cambiado de Long a EstudianteDTO
    private MateriaDTO materia;     // Cambiado de Long a MateriaDTO
}