package pe.edu.upeu.exa1lp2.service.service;

import pe.edu.upeu.exa1lp2.dto.EstudianteDTO;
import java.util.List;

public interface EstudianteService {
    List<EstudianteDTO> findAll();
    EstudianteDTO findById(Long id);
    EstudianteDTO create(EstudianteDTO estudianteDTO);
    EstudianteDTO update(Long id, EstudianteDTO estudianteDTO);
    void deleteById(Long id);
}