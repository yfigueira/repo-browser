package com.example.repobrowser.common.exception;

import lombok.Builder;

@Builder
public record ErrorResponse(
        Integer status,
        String message
) {
}
