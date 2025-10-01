package pe.edu.upeu.exa1lp2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.exa1lp2.dto.MateriaDTO;
import pe.edu.upeu.exa1lp2.entity.Materia;
import pe.edu.upeu.exa1lp2.mappers.MateriaMapper;
import pe.edu.upeu.exa1lp2.repository.MateriaRepository;
import pe.edu.upeu.exa1lp2.service.service.MateriaService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MateriaServiceImpl implements MateriaService {

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private MateriaMapper materiaMapper;

    @Override
    public List<MateriaDTO> findAll() {
        return materiaRepository.findAll().stream()
                .map(materiaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MateriaDTO findById(Long id) {
        Materia materia = materiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada"));
        return materiaMapper.toDto(materia);
    }

    @Override
    public MateriaDTO create(MateriaDTO materiaDTO) {
        Materia materia = materiaMapper.toEntity(materiaDTO);
        materia = materiaRepository.save(materia);
        return materiaMapper.toDto(materia);
    }

    @Override
    public MateriaDTO update(Long id, MateriaDTO materiaDTO) {
        Materia materiaExistente = materiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada"));
        materiaExistente.setNombreMateria(materiaDTO.getNombreMateria());
        materiaExistente = materiaRepository.save(materiaExistente);
        return materiaMapper.toDto(materiaExistente);
    }

    @Override
    public void deleteById(Long id) {
        materiaRepository.deleteById(id);
    }
}