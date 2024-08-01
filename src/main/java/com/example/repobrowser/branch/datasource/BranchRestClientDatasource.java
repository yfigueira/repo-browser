package com.example.repobrowser.branch.datasource;

import com.example.repobrowser.branch.domain.Branch;
import com.example.repobrowser.branch.domain.BranchDatasource;
import com.example.repobrowser.repository.domain.Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
@RequiredArgsConstructor
class BranchRestClientDatasource implements BranchDatasource {

    private final RestClient restClient;

    @Override
    public List<Branch> findByRepository(Repository repository) {
        return restClient.get()
                .uri("repos/{owner}/{repo}/branches", repository.owner().login(), repository.name())
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}
