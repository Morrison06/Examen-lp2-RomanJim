package pe.edu.upeu.exa1lp2.service.impl;

import pe.edu.upeu.exa1lp2.dto.EquipoDTO;
import pe.edu.upeu.exa1lp2.entity.Equipo;
import pe.edu.upeu.exa1lp2.mappers.EquipoMapper;
import pe.edu.upeu.exa1lp2.repository.EquipoRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import pe.edu.upeu.exa1lp2.service.service.EquipoService;

import java.util.List;

@Service
public class EquipoServiceImpl implements EquipoService {

    private final EquipoRepository equipoRepository;
    private final EquipoMapper equipoMapper;

    public EquipoServiceImpl(EquipoRepository equipoRepository, EquipoMapper equipoMapper) {
        this.equipoRepository = equipoRepository;
        this.equipoMapper = equipoMapper;
    }

    @Override
    public EquipoDTO create(EquipoDTO equipoDTO) throws ServiceException {
        try {
            Equipo equipo = equipoMapper.toEntity(equipoDTO);
            Equipo equipoSaved = equipoRepository.save(equipo);
            return equipoMapper.toDTO(equipoSaved);
        } catch(Exception e) {
            throw new ServiceException("Error al crear Equipo: " + e.getMessage());
        }
    }

    @Override
    public EquipoDTO update(Long id, EquipoDTO equipoDTO) throws ServiceException {
        try {
            Equipo equipoExistente = equipoRepository.findById(id)
                    .orElseThrow(() -> new ServiceException("No existe el equipo con id: " + id));

            equipoExistente.setDescripcion(equipoDTO.getDescripcion());
            equipoExistente.setCantidad(equipoDTO.getCantidad());
            equipoExistente.setPrecio(equipoDTO.getPrecio());

            return equipoMapper.toDTO(equipoRepository.save(equipoExistente));
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar el equipo: " + e.getMessage());
        }
    }

    @Override
    public EquipoDTO findById(Long id) throws ServiceException {
        try {
            Equipo equipo = equipoRepository.findById(id)
                    .orElseThrow(() -> new ServiceException("No existe el equipo con id: " + id));
            return equipoMapper.toDTO(equipo);
        } catch (Exception e) {
            throw new ServiceException("Error al leer el equipo con id " + id, e);
        }
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        try {
            if (!equipoRepository.existsById(id)) {
                throw new ServiceException("No existe el equipo con id: " + id);
            }
            equipoRepository.deleteById(id);
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar el equipo con id " + id, e);
        }
    }

    @Override
    public List<EquipoDTO> findAll() throws ServiceException {
        try {
            return equipoMapper.toDTOs(equipoRepository.findAll());
        } catch (Exception e) {
            throw new ServiceException("Error al listar los equipos: " + e.getMessage());
        }
    }
}