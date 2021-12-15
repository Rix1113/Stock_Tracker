package com.rix.stock_tracker.converter;

public interface MappingOperations <E,D>{
    D fromEntityToDto(E entity);
    E fromDtoToEntity(D dto);
}
