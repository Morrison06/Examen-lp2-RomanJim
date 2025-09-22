package pe.edu.upeu.exa1lp2.mappers;

import org.mapstruct.Mapper;
import pe.edu.upeu.exa1lp2.dto.EquipoDTO;
import pe.edu.upeu.exa1lp2.entity.Equipo;
import pe.edu.upeu.exa1lp2.mappers.base.BaseMappers;

@Mapper(componentModel = "spring")
public interface EquipoMapper extends BaseMappers<Equipo, EquipoDTO> {
}
