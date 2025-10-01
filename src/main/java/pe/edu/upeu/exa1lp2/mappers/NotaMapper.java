package pe.edu.upeu.exa1lp2.mappers;

import org.mapstruct.Mapper;
import pe.edu.upeu.exa1lp2.dto.NotaDTO;
import pe.edu.upeu.exa1lp2.entity.Nota;

// Le decimos a este mapper que puede usar los otros mappers como ayudantes
@Mapper(componentModel = "spring", uses = {EstudianteMapper.class, MateriaMapper.class})
public interface NotaMapper {
    // MapStruct usará automáticamente EstudianteMapper y MateriaMapper
    // para convertir los objetos anidados. ¡No necesitas hacer más!
    NotaDTO toDto(Nota nota);
    Nota toEntity(NotaDTO notaDTO);
}