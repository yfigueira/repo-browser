package com.example.repobrowser.repository.domain;

import com.example.repobrowser.branch.domain.Branch;
import com.example.repobrowser.branch.domain.Commit;
import com.example.repobrowser.repository.exception.RepositoryException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RepositoryServiceTest {

    @InjectMocks
    private RepositoryService repositoryService;

    @Mock
    private RepositoryDatasource repositoryDatasource;


    @Test
    void whenGivenValidUsername_shouldReturnListOfRepositories() {
        // given
        String validUsername = "test-username";
        List<Repository> mockRepositories = mockRepositories();

        doReturn(mockRepositories).when(repositoryDatasource).findByUsername(validUsername);

        // when
        List<Repository> repositories = repositoryService.getByUsername(validUsername);

        // then
        verify(repositoryDatasource, times(1)).findByUsername(validUsername);
        assertThat(repositories, hasSize(1));
        assertThat(repositories.get(0).branches(), hasSize(2));
    }

    @Test
    void whenUsernameIsMissing_shouldThrowMissingArgumentException() {
        assertThrows(RepositoryException.MissingArgumentException.class, () -> repositoryService.getByUsername(null));
        assertThrows(RepositoryException.MissingArgumentException.class, () -> repositoryService.getByUsername(""));
    }

    private List<Repository> mockRepositories() {
        return List.of(
                new Repository("test repo", new Owner("test login"), List.of(
                        new Branch("branch 1", new Commit("sha 1")),
                        new Branch("branch 2", new Commit("sha 2"))
                ))
        );
    }
}