package pe.edu.upeu.exa1lp2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name="TBL_EMPRESAS")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "NOMBRE", unique = true, nullable = false, length = 255)
    private String nombre;

    @Column(name = "RUC", unique = true, nullable = false, length = 11)
    private String ruc;

    @Column(name = "DIRECCION", length = 200)
    private String direccion;
}
