package com.example.repobrowser.repository.domain;

import com.example.repobrowser.branch.domain.Branch;
import lombok.With;

import java.util.List;

public record Repository(
        String name,
        Owner owner,
        @With List<Branch> branches
) {
}
