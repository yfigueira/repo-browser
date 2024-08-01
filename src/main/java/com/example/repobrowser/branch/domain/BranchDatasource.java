package com.example.repobrowser.branch.domain;

import com.example.repobrowser.repository.domain.Repository;

import java.util.List;

public interface BranchDatasource {

    List<Branch> findByRepository(Repository repository);
}
