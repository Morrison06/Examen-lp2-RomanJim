package pe.edu.upeu.exa1lp2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.exa1lp2.entity.Nota;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {
}