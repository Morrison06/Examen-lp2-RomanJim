package pe.edu.upeu.exa1lp2.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Materias")
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdMateria")
    private Long id;

    @Column(name = "Materia")
    private String nombreMateria;
}