package pe.edu.upeu.exa1lp2.service.impl;

import pe.edu.upeu.exa1lp2.controller.exceptions.ResourceNotFoundException;
import pe.edu.upeu.exa1lp2.dto.EmpresaDTO;
import pe.edu.upeu.exa1lp2.entity.Empresa;
import pe.edu.upeu.exa1lp2.mappers.EmpresaMapper;
import pe.edu.upeu.exa1lp2.repository.EmpresaRepository;
import pe.edu.upeu.exa1lp2.service.service.EmpresaService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final EmpresaMapper empresaMapper;

    public EmpresaServiceImpl(EmpresaRepository empresaRepository, EmpresaMapper empresaMapper) {
        this.empresaRepository = empresaRepository;
        this.empresaMapper = empresaMapper;
    }

    @Override
    public EmpresaDTO create(EmpresaDTO empresaDTO) throws ServiceException {
        try {
            Empresa empresa = empresaMapper.toEntity(empresaDTO);
            Empresa empresaSaved = empresaRepository.save(empresa);
            return empresaMapper.toDTO(empresaSaved);
        } catch(Exception e) {
            throw new ServiceException("Error al crear Empresa: " + e.getMessage());
        }
    }

    @Override
    public EmpresaDTO update(Long id, EmpresaDTO empresaDTO) throws ServiceException {
        try {
            Empresa empresaExistente = empresaRepository.findById(id)
                    .orElseThrow(() -> new ServiceException("No existe la empresa con id: " + id));

            empresaExistente.setNombre(empresaDTO.getNombre());
            empresaExistente.setRuc(empresaDTO.getRuc());
            empresaExistente.setDireccion(empresaDTO.getDireccion());

            return empresaMapper.toDTO(empresaRepository.save(empresaExistente));
        } catch (ResourceNotFoundException e) {
            throw (e);
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar la empresa: " + e.getMessage());
        }
    }

    @Override
    public EmpresaDTO findById(Long id) throws ServiceException {
        try {
            Empresa empresa = empresaRepository.findById(id)
                    .orElseThrow(() -> new ServiceException("No existe la empresa con id: " + id));
            return empresaMapper.toDTO(empresa);
        } catch (ResourceNotFoundException e) {
            throw (e);
        } catch (Exception e) {
            throw new ServiceException("Error al leer la empresa con id " + id, e);
        }
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        try {
            if (!empresaRepository.existsById(id)) {
                throw new ServiceException("No existe la empresa con id: " + id);
            }
            empresaRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw (e);
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar la empresa con id " + id, e);
        }
    }

    @Override
    public List<EmpresaDTO> findAll() throws ServiceException {
        try {
            return empresaMapper.toDTOs(empresaRepository.findAll());
        } catch (Exception e) {
            throw new ServiceException("Error al listar las empresas: " + e.getMessage());
        }
    }
}
