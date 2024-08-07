package com.example.repobrowser.branch.domain;

import com.example.repobrowser.repository.domain.Repository;
import reactor.core.publisher.Flux;

public interface BranchDatasource {

    Flux<Branch> findByRepository(Repository repository);
}
