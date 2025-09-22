package pe.edu.upeu.exa1lp2.mappers;

import org.mapstruct.Mapper;
import pe.edu.upeu.exa1lp2.dto.EmpresaDTO;
import pe.edu.upeu.exa1lp2.entity.Empresa;
import pe.edu.upeu.exa1lp2.mappers.base.BaseMappers;
@Mapper(componentModel = "spring")
public interface EmpresaMapper extends BaseMappers<Empresa, EmpresaDTO> {

}
