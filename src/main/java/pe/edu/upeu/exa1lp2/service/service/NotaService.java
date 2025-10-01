package pe.edu.upeu.exa1lp2.service.service;

import pe.edu.upeu.exa1lp2.dto.NotaDTO;
import java.util.List;

public interface NotaService {
    List<NotaDTO> findAll();
    NotaDTO findById(Long id);
    NotaDTO create(NotaDTO notaDTO);
    // Nota: Update para esta tabla puede ser complejo, lo omitiremos por simplicidad
    void deleteById(Long id);
}