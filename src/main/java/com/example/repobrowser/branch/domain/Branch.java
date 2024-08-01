package com.example.repobrowser.branch.domain;

public record Branch(
    String name,
    Commit commit
) {
}
