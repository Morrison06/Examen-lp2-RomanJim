package pe.edu.upeu.exa1lp2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.exa1lp2.dto.EstudianteDTO;
import pe.edu.upeu.exa1lp2.entity.Estudiante;
import pe.edu.upeu.exa1lp2.mappers.EstudianteMapper;
import pe.edu.upeu.exa1lp2.repository.EstudianteRepository;
import pe.edu.upeu.exa1lp2.service.service.EstudianteService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private EstudianteMapper estudianteMapper;

    @Override
    public List<EstudianteDTO> findAll() {
        return estudianteRepository.findAll()
                .stream()
                .map(estudianteMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EstudianteDTO findById(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + id));
        return estudianteMapper.toDto(estudiante);
    }

    @Override
    public EstudianteDTO create(EstudianteDTO estudianteDTO) {
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        estudiante = estudianteRepository.save(estudiante);
        return estudianteMapper.toDto(estudiante);
    }

    @Override
    public EstudianteDTO update(Long id, EstudianteDTO estudianteDTO) {
        Estudiante estudianteExistente = estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + id));

        estudianteExistente.setNombres(estudianteDTO.getNombres());
        estudianteExistente.setApellidos(estudianteDTO.getApellidos());
        estudianteExistente.setDireccion(estudianteDTO.getDireccion());
        estudianteExistente.setTelefono(estudianteDTO.getTelefono());

        estudianteExistente = estudianteRepository.save(estudianteExistente);
        return estudianteMapper.toDto(estudianteExistente);
    }

    @Override
    public void deleteById(Long id) {
        if (!estudianteRepository.existsById(id)) {
            throw new RuntimeException("Estudiante no encontrado con ID: " + id);
        }
        estudianteRepository.deleteById(id);
    }
}