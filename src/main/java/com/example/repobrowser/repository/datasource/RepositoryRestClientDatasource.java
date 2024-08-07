package com.example.repobrowser.repository.datasource;

import com.example.repobrowser.branch.domain.BranchDatasource;
import com.example.repobrowser.repository.domain.Repository;
import com.example.repobrowser.repository.domain.RepositoryDatasource;
import com.example.repobrowser.repository.exception.RepositoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
class RepositoryRestClientDatasource implements RepositoryDatasource {

    private final WebClient webClient;
    private final BranchDatasource branchDatasource;
    private final RepositoryMapper mapper;

    @Override
    public Flux<Repository> findByUsername(String username) {
        return webClient.get()
                .uri("users/{username}/repos", username)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.error(RepositoryException.usernameNotFound(username)))
                .bodyToFlux(RepositoryEntity.class)
                .filter(repositoryEntity -> !repositoryEntity.fork())
                .map(mapper::toDomain)
                .flatMap(this::fetchBranches);
    }

    private Mono<Repository> fetchBranches(Repository repository) {
        return branchDatasource.findByRepository(repository)
                .collectList()
                .map(repository::withBranches);
    }
}
