package pe.edu.upeu.exa1lp2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.exa1lp2.entity.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa,Long> {
}
