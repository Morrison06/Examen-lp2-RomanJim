package pe.edu.upeu.exa1lp2.mappers.base;


import java.util.List;

public interface BaseMappers<E, D> {
    D toDTO(E entity);
    E toEntity(D dto);
    List<D> toDTOs(List<E> list);
    List<E> toEntityList(List<D> list);
}
