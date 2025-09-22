package pe.edu.upeu.exa1lp2.mappers;

import pe.edu.upeu.exa1lp2.dto.DetalleAlquilerDTO;
import pe.edu.upeu.exa1lp2.entity.DetalleAlquiler;
import pe.edu.upeu.exa1lp2.mappers.base.BaseMappers;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DetalleAlquilerMapper extends BaseMappers<DetalleAlquiler, DetalleAlquilerDTO> {

    // Para convertir DTO → Entity, IGNORAR las relaciones (se manejan en el servicio)
    @Mapping(target = "alquiler", ignore = true)
    @Mapping(target = "equipo", ignore = true)
    DetalleAlquiler toEntity(DetalleAlquilerDTO detalleAlquilerDTO);

    // Para convertir Entity → DTO, incluir las relaciones
    @Mapping(source = "alquiler", target = "alquiler")
    @Mapping(source = "equipo", target = "equipo")
    DetalleAlquilerDTO toDTO(DetalleAlquiler detalleAlquiler);
}
