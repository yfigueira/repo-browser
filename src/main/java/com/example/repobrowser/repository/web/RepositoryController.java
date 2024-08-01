package com.example.repobrowser.repository.web;

import com.example.repobrowser.repository.domain.RepositoryService;
import com.example.repobrowser.repository.web.dto.RepositoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/repositories")
@RequiredArgsConstructor
public class RepositoryController {

    private final RepositoryService repositoryService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<RepositoryDTO> getByUsername(@RequestParam String username) {
        var repositories = repositoryService.getByUsername(username);
        return repositories.stream()
                .map(RepositoryDTO.mapper()::toDto)
                .toList();
    }
}
