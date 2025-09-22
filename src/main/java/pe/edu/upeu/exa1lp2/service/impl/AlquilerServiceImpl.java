package pe.edu.upeu.exa1lp2.service.impl;

import pe.edu.upeu.exa1lp2.dto.AlquilerDTO;
import pe.edu.upeu.exa1lp2.entity.Alquiler;
import pe.edu.upeu.exa1lp2.entity.Empresa;
import pe.edu.upeu.exa1lp2.mappers.AlquilerMapper;
import pe.edu.upeu.exa1lp2.repository.AlquilerRepository;
import pe.edu.upeu.exa1lp2.repository.EmpresaRepository;
import pe.edu.upeu.exa1lp2.service.service.AlquilerService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class AlquilerServiceImpl implements AlquilerService {

    private final AlquilerRepository alquilerRepository;
    private final AlquilerMapper alquilerMapper;
    private final EmpresaRepository empresaRepository;

    public AlquilerServiceImpl(AlquilerRepository alquilerRepository,
                               AlquilerMapper alquilerMapper,
                               EmpresaRepository empresaRepository) {
        this.alquilerRepository = alquilerRepository;
        this.alquilerMapper = alquilerMapper;
        this.empresaRepository = empresaRepository;
    }

    @Override
    @Transactional
    public AlquilerDTO create(AlquilerDTO alquilerDTO) throws ServiceException {
        try {
            // Validaciones básicas
            if (alquilerDTO == null) {
                throw new ServiceException("El DTO del alquiler no puede ser nulo");
            }

            // Validar empresa
            if (alquilerDTO.getEmpresa() == null || alquilerDTO.getEmpresa().getId() == null) {
                throw new ServiceException("La empresa es requerida");
            }

            // Verificar existencia de la empresa
            Empresa empresa = empresaRepository.findById(alquilerDTO.getEmpresa().getId())
                    .orElseThrow(() -> new ServiceException("No existe la empresa con id: " + alquilerDTO.getEmpresa().getId()));

            // Validar fechas
            if (alquilerDTO.getFechaSalida() == null) {
                throw new ServiceException("La fecha de salida es requerida");
            }

            // Convertir DTO a entidad
            Alquiler alquiler = alquilerMapper.toEntity(alquilerDTO);
            alquiler.setEmpresa(empresa);

            // Guardar en la base de datos
            Alquiler alquilerSaved = alquilerRepository.save(alquiler);

            // Convertir a DTO y retornar
            return alquilerMapper.toDTO(alquilerSaved);

        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al crear Alquiler: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public AlquilerDTO update(Long id, AlquilerDTO alquilerDTO) throws ServiceException {
        try {
            // Validaciones básicas
            if (id == null) {
                throw new ServiceException("El ID no puede ser nulo");
            }
            if (alquilerDTO == null) {
                throw new ServiceException("El DTO del alquiler no puede ser nulo");
            }

            // Buscar el alquiler existente
            Alquiler alquilerExistente = alquilerRepository.findById(id)
                    .orElseThrow(() -> new ServiceException("No existe el alquiler con id: " + id));

            // Actualizar campos básicos
            if (alquilerDTO.getFechaSalida() != null) {
                alquilerExistente.setFechaSalida(alquilerDTO.getFechaSalida());
            }
            if (alquilerDTO.getFechaEntrada() != null) {
                alquilerExistente.setFechaEntrada(alquilerDTO.getFechaEntrada());
            }
            if (alquilerDTO.getObservacion() != null) {
                alquilerExistente.setObservacion(alquilerDTO.getObservacion());
            }

            // **ACTUALIZACIÓN DE EMPRESA - CORREGIDO**
            if (alquilerDTO.getEmpresa() != null && alquilerDTO.getEmpresa().getId() != null) {
                // Verificar si la empresa ha cambiado
                if (alquilerExistente.getEmpresa() == null ||
                        !Objects.equals(alquilerExistente.getEmpresa().getId(), alquilerDTO.getEmpresa().getId())) {

                    // Buscar la nueva empresa
                    Empresa nuevaEmpresa = empresaRepository.findById(alquilerDTO.getEmpresa().getId())
                            .orElseThrow(() -> new ServiceException("No existe la empresa con id: " + alquilerDTO.getEmpresa().getId()));

                    // Actualizar la relación con la nueva empresa
                    alquilerExistente.setEmpresa(nuevaEmpresa);
                }
                // Si la empresa es la misma, no hacemos nada
            } else {
                // Si no se proporciona empresa en el DTO, mantener la actual
                // O lanzar excepción si quieres que sea requerida
                // throw new ServiceException("La empresa es requerida");
            }

            // Guardar cambios
            Alquiler alquilerActualizado = alquilerRepository.save(alquilerExistente);

            // Retornar DTO actualizado
            return alquilerMapper.toDTO(alquilerActualizado);

        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar el alquiler con id " + id + ": " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public AlquilerDTO findById(Long id) throws ServiceException {
        try {
            if (id == null) {
                throw new ServiceException("El ID no puede ser nulo");
            }

            Alquiler alquiler = alquilerRepository.findById(id)
                    .orElseThrow(() -> new ServiceException("No existe el alquiler con id: " + id));

            return alquilerMapper.toDTO(alquiler);

        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al buscar el alquiler con id " + id + ": " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws ServiceException {
        try {
            if (id == null) {
                throw new ServiceException("El ID no puede ser nulo");
            }

            if (!alquilerRepository.existsById(id)) {
                throw new ServiceException("No existe el alquiler con id: " + id);
            }

            alquilerRepository.deleteById(id);

        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar el alquiler con id " + id + ": " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<AlquilerDTO> findAll() throws ServiceException {
        try {
            List<Alquiler> alquileres = alquilerRepository.findAll();
            return alquilerMapper.toDTOs(alquileres);

        } catch (Exception e) {
            throw new ServiceException("Error al listar los alquileres: " + e.getMessage(), e);
        }
    }
}