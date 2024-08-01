package com.example.repobrowser.repository.web.dto;

import com.example.repobrowser.branch.domain.Branch;
import com.example.repobrowser.repository.domain.Owner;
import com.example.repobrowser.repository.domain.Repository;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

public record RepositoryDTO(
        String name,
        Owner owner,
        List<Branch> branches
) {
    @Mapper(componentModel = "spring")
    public interface RepositoryDTOMapper {
        RepositoryDTO toDto(Repository domain);
    }

    public static RepositoryDTOMapper mapper() {
        return Mappers.getMapper(RepositoryDTO.RepositoryDTOMapper.class);
    }
}
