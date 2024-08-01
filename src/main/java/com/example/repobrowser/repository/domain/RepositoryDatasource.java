package com.example.repobrowser.repository.domain;

import java.util.List;

public interface RepositoryDatasource {

    List<Repository> findByUsername(String username);
}
