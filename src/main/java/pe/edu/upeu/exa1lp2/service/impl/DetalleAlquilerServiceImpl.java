package pe.edu.upeu.exa1lp2.service.impl;

import pe.edu.upeu.exa1lp2.dto.DetalleAlquilerDTO;
import pe.edu.upeu.exa1lp2.entity.Alquiler;
import pe.edu.upeu.exa1lp2.entity.DetalleAlquiler;
import pe.edu.upeu.exa1lp2.entity.Equipo;
import pe.edu.upeu.exa1lp2.mappers.DetalleAlquilerMapper;
import pe.edu.upeu.exa1lp2.repository.AlquilerRepository;
import pe.edu.upeu.exa1lp2.repository.DetalleAlquilerRepository;
import pe.edu.upeu.exa1lp2.repository.EquipoRepository;
import pe.edu.upeu.exa1lp2.service.service.DetalleAlquilerService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleAlquilerServiceImpl implements DetalleAlquilerService {

    private final DetalleAlquilerRepository detalleAlquilerRepository;
    private final DetalleAlquilerMapper detalleAlquilerMapper;
    private final AlquilerRepository alquilerRepository;
    private final EquipoRepository equipoRepository;

    public DetalleAlquilerServiceImpl(DetalleAlquilerRepository detalleAlquilerRepository,
                                      DetalleAlquilerMapper detalleAlquilerMapper,
                                      AlquilerRepository alquilerRepository,
                                      EquipoRepository equipoRepository) {
        this.detalleAlquilerRepository = detalleAlquilerRepository;
        this.detalleAlquilerMapper = detalleAlquilerMapper;
        this.alquilerRepository = alquilerRepository;
        this.equipoRepository = equipoRepository;
    }
    @Override
    public DetalleAlquilerDTO create(DetalleAlquilerDTO detalleAlquilerDTO) throws ServiceException {
        try {
            DetalleAlquiler detalleAlquiler = detalleAlquilerMapper.toEntity(detalleAlquilerDTO);

            // Asignar entidades completas
            if (detalleAlquilerDTO.getAlquiler() != null && detalleAlquilerDTO.getAlquiler().getId() != null) {
                Alquiler alquiler = alquilerRepository.findById(detalleAlquilerDTO.getAlquiler().getId())
                        .orElseThrow(() -> new ServiceException("No existe el alquiler con id: " + detalleAlquilerDTO.getAlquiler().getId()));
                detalleAlquiler.setAlquiler(alquiler);
            }
            if (detalleAlquilerDTO.getEquipo() != null && detalleAlquilerDTO.getEquipo().getId() != null) {
                Equipo equipo = equipoRepository.findById(detalleAlquilerDTO.getEquipo().getId())
                        .orElseThrow(() -> new ServiceException("No existe el equipo con id: " + detalleAlquilerDTO.getEquipo().getId()));
                detalleAlquiler.setEquipo(equipo);
            }

            DetalleAlquiler detalleSaved = detalleAlquilerRepository.save(detalleAlquiler);
            return detalleAlquilerMapper.toDTO(detalleSaved);
        } catch (Exception e) {
            throw new ServiceException("Error al crear DetalleAlquiler: " + e.getMessage());
        }
    }

    @Override
    public DetalleAlquilerDTO update(Long id, DetalleAlquilerDTO detalleAlquilerDTO) throws ServiceException {
        try {
            DetalleAlquiler detalleExistente = detalleAlquilerRepository.findById(id)
                    .orElseThrow(() -> new ServiceException("No existe el detalle con id: " + id));

            // Actualizar campos básicos
            detalleExistente.setCantidad(detalleAlquilerDTO.getCantidad());
            detalleExistente.setPrecio(detalleAlquilerDTO.getPrecio());

            // ACTUALIZAR RELACIÓN CON ALQUILER - Buscar la entidad completa
            if (detalleAlquilerDTO.getAlquiler() != null && detalleAlquilerDTO.getAlquiler().getId() != null) {
                Alquiler alquiler = alquilerRepository.findById(detalleAlquilerDTO.getAlquiler().getId())
                        .orElseThrow(() -> new ServiceException("No existe el alquiler con id: " + detalleAlquilerDTO.getAlquiler().getId()));
                detalleExistente.setAlquiler(alquiler); // ✅ Asignar la entidad completa
            }
            if (detalleAlquilerDTO.getEquipo() != null && detalleAlquilerDTO.getEquipo().getId() != null) {
                Equipo equipo = equipoRepository.findById(detalleAlquilerDTO.getEquipo().getId())
                        .orElseThrow(() -> new ServiceException("No existe el equipo con id: " + detalleAlquilerDTO.getEquipo().getId()));
                detalleExistente.setEquipo(equipo); // ✅ Asignar la entidad completa
            }

            DetalleAlquiler detalleActualizado = detalleAlquilerRepository.save(detalleExistente);
            return detalleAlquilerMapper.toDTO(detalleActualizado);

        } catch (Exception e) {
            throw new ServiceException("Error al actualizar el detalle: " + e.getMessage());
        }
    }

    @Override
    public DetalleAlquilerDTO findById(Long id) throws ServiceException {
        try {
            DetalleAlquiler detalleAlquiler = detalleAlquilerRepository.findById(id)
                    .orElseThrow(() -> new ServiceException("No existe el detalle con id: " + id));
            return detalleAlquilerMapper.toDTO(detalleAlquiler);
        } catch (Exception e) {
            throw new ServiceException("Error al leer el detalle con id " + id, e);
        }
    }

    @Override
    public void deleteById(Long id) throws ServiceException {  // ✅ ESTE MÉTODO FALTABA
        try {
            if (!detalleAlquilerRepository.existsById(id)) {
                throw new ServiceException("No existe el detalle con id: " + id);
            }
            detalleAlquilerRepository.deleteById(id);
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar el detalle con id " + id, e);
        }
    }

    @Override
    public List<DetalleAlquilerDTO> findAll() throws ServiceException {
        try {
            return detalleAlquilerMapper.toDTOs(detalleAlquilerRepository.findAll());
        } catch (Exception e) {
            throw new ServiceException("Error al listar los detalles: " + e.getMessage());
        }
    }
}