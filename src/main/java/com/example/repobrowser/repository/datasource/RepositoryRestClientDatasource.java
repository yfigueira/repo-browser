package com.example.repobrowser.repository.datasource;

import com.example.repobrowser.branch.domain.Branch;
import com.example.repobrowser.branch.domain.BranchDatasource;
import com.example.repobrowser.repository.domain.Repository;
import com.example.repobrowser.repository.domain.RepositoryDatasource;
import com.example.repobrowser.repository.exception.RepositoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
class RepositoryRestClientDatasource implements RepositoryDatasource {

    private final RestClient restClient;
    private final BranchDatasource branchDatasource;
    private final RepositoryMapper mapper;

    @Override
    public List<Repository> findByUsername(String username) {
        List<RepositoryEntity> repos = restClient.get()
                .uri("users/{username}/repos", username)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, ((request, response) -> {
                    throw RepositoryException.usernameNotFound(username);
                }))
                .body(new ParameterizedTypeReference<>() {});

        return Optional.ofNullable(repos).stream()
                .flatMap(Collection::stream)
                .filter(repo -> !repo.fork())
                .map(mapper::toDomain)
                .map(this::fetchBranches)
                .toList();
    }

    private Repository fetchBranches(Repository repository) {
        List<Branch> branches = branchDatasource.findByRepository(repository);
        return repository.withBranches(branches);
    }
}
