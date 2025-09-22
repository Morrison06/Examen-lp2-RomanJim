package pe.edu.upeu.exa1lp2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.exa1lp2.entity.Alquiler;

import java.util.Optional;

@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {

    // Agrega este método para refrescar la entidad
    @Query("SELECT a FROM Alquiler a LEFT JOIN FETCH a.empresa WHERE a.id = :id")
    Optional<Alquiler> findByIdWithEmpresa(@Param("id") Long id);
}
