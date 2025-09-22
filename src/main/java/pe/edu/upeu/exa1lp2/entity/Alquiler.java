package pe.edu.upeu.exa1lp2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "TBL_ALQUILER")
public class Alquiler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FECHA_SALIDA", nullable = false)
    private LocalDate fechaSalida;

    @Column(name = "FECHA_ENTRADA")
    private LocalDate fechaEntrada;

    @Column(name = "OBSERVACION", length = 200)
    private String observacion;

    @ManyToOne
    @JoinColumn(name = "EMPRESA_ID", nullable = false)
    private Empresa empresa;

    @OneToMany(mappedBy = "alquiler", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<DetalleAlquiler> detalles = new ArrayList<>();

}
