package com.example.repobrowser.branch.datasource;

import com.example.repobrowser.branch.domain.Branch;
import com.example.repobrowser.branch.domain.BranchDatasource;
import com.example.repobrowser.repository.domain.Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
class BranchRestClientDatasource implements BranchDatasource {

    private final WebClient webClient;

    @Override
    public Flux<Branch> findByRepository(Repository repository) {
        return webClient.get()
                .uri("repos/{owner}/{repo}/branches", repository.owner().login(), repository.name())
                .retrieve()
                .bodyToFlux(Branch.class);
    }
}
