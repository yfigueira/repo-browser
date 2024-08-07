package com.example.repobrowser.repository.domain;

import reactor.core.publisher.Flux;

public interface RepositoryDatasource {

    Flux<Repository> findByUsername(String username);
}
