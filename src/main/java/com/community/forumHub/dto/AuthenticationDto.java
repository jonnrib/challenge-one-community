package com.community.forumHub.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDto(
        @NotBlank
        String email,

        @NotBlank
        String password) {
}
