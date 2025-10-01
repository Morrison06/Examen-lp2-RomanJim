package pe.edu.upeu.exa1lp2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.exa1lp2.dto.NotaDTO;
import pe.edu.upeu.exa1lp2.entity.Estudiante;
import pe.edu.upeu.exa1lp2.entity.Materia;
import pe.edu.upeu.exa1lp2.entity.Nota;
import pe.edu.upeu.exa1lp2.mappers.NotaMapper;
import pe.edu.upeu.exa1lp2.repository.EstudianteRepository;
import pe.edu.upeu.exa1lp2.repository.MateriaRepository;
import pe.edu.upeu.exa1lp2.repository.NotaRepository;
import pe.edu.upeu.exa1lp2.service.service.NotaService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotaServiceImpl implements NotaService {

    @Autowired
    private NotaRepository notaRepository;
    @Autowired
    private EstudianteRepository estudianteRepository;
    @Autowired
    private MateriaRepository materiaRepository;
    @Autowired
    private NotaMapper notaMapper;

    @Override
    public List<NotaDTO> findAll() {
        return notaRepository.findAll().stream()
                .map(notaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public NotaDTO findById(Long id) {
        Nota nota = notaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota no encontrada"));
        return notaMapper.toDto(nota);
    }

    @Override
    public NotaDTO create(NotaDTO notaDTO) {
        // ----- LA CORRECCIÓN ESTÁ AQUÍ -----
        // Ahora leemos el ID desde el objeto anidado que viene en el DTO
        Estudiante estudiante = estudianteRepository.findById(notaDTO.getEstudiante().getId())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        Materia materia = materiaRepository.findById(notaDTO.getMateria().getId())
                .orElseThrow(() -> new RuntimeException("Materia no encontrada"));
        // ------------------------------------

        Nota nota = notaMapper.toEntity(notaDTO);
        nota.setEstudiante(estudiante);
        nota.setMateria(materia);

        nota = notaRepository.save(nota);
        return notaMapper.toDto(nota);
    }

    @Override
    public void deleteById(Long id) {
        if (!notaRepository.existsById(id)) {
            throw new RuntimeException("Nota no encontrada con ID: " + id);
        }
        notaRepository.deleteById(id);
    }
}