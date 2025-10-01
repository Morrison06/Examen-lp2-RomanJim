package pe.edu.upeu.exa1lp2.mappers;

import org.mapstruct.Mapper;
import pe.edu.upeu.exa1lp2.dto.MateriaDTO;
import pe.edu.upeu.exa1lp2.entity.Materia;
import pe.edu.upeu.exa1lp2.mappers.base.BaseMappers;

@Mapper(componentModel = "spring")
public interface MateriaMapper extends BaseMappers<Materia, MateriaDTO> {
}