package pe.edu.upeu.exa1lp2.service.service;

import pe.edu.upeu.exa1lp2.dto.MateriaDTO;
import java.util.List;

public interface MateriaService {
    List<MateriaDTO> findAll();
    MateriaDTO findById(Long id);
    MateriaDTO create(MateriaDTO materiaDTO);
    MateriaDTO update(Long id, MateriaDTO materiaDTO);
    void deleteById(Long id);
}