package pe.edu.upeu.exa1lp2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.exa1lp2.entity.Materia;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long> {
}