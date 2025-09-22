package pe.edu.upeu.exa1lp2.mappers;

import pe.edu.upeu.exa1lp2.dto.AlquilerDTO;
import pe.edu.upeu.exa1lp2.entity.Alquiler;
import pe.edu.upeu.exa1lp2.mappers.base.BaseMappers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {EmpresaMapper.class})
public interface AlquilerMapper extends BaseMappers<Alquiler, AlquilerDTO> {
}