package com.example.repobrowser.repository.datasource;

import com.example.repobrowser.repository.datasource.RepositoryEntity;
import com.example.repobrowser.repository.domain.Repository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RepositoryMapper {

    @Mapping(target = "branches", ignore = true)
    Repository toDomain(RepositoryEntity entity);
}
