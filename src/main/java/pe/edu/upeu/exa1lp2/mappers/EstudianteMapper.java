package pe.edu.upeu.exa1lp2.mappers; // <-- CORREGIDO a plural "mappers"

import org.mapstruct.Mapper;
import pe.edu.upeu.exa1lp2.dto.EstudianteDTO;
import pe.edu.upeu.exa1lp2.entity.Estudiante;
import pe.edu.upeu.exa1lp2.mappers.base.BaseMappers;

@Mapper(componentModel = "spring")
public interface EstudianteMapper extends BaseMappers<Estudiante, EstudianteDTO> {
    // Se deja vacío a propósito porque hereda todo de BaseMappers.
}