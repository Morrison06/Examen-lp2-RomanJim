package pe.edu.upeu.exa1lp2.mappers.base;

import java.util.List;

public interface BaseMappers<E, D> {
    D toDto(E entity); // Corregido a minúsculas
    E toEntity(D dto);
    List<D> toDtoList(List<E> list); // Corregido a un nombre más estándar
    List<E> toEntityList(List<D> list);
}