package com.example.repobrowser.repository.domain;

import com.example.repobrowser.repository.exception.RepositoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RepositoryService {

    private final RepositoryDatasource repositoryDatasource;

    public List<Repository> getByUsername(String username) {
            if (username == null || username.isEmpty()) throw RepositoryException.missingUsernameArgument();
            return repositoryDatasource.findByUsername(username)
                    .collectList()
                    .block();
    }
}
