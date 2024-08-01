package com.example.repobrowser.repository.datasource;

import com.example.repobrowser.branch.domain.Branch;
import com.example.repobrowser.repository.domain.Owner;

import java.util.List;

public record RepositoryEntity(
    String name,
    Owner owner,
    boolean fork,
    List<Branch> branches
) {
}
