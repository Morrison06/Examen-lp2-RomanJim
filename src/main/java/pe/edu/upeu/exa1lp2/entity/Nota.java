package pe.edu.upeu.exa1lp2.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Notas")
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Agregamos un ID propio para la tabla de notas

    @ManyToOne
    @JoinColumn(name = "IdEstudiante")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "IdMateria")
    private Materia materia;

    @Column(name = "Nota")
    private Double nota;
}