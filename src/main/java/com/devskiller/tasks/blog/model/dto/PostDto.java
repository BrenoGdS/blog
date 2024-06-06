package com.devskiller.tasks.blog.model.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record PostDto(@NotBlank String title,@NotBlank String content, LocalDateTime creationDate) {
}
