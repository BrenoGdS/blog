package com.devskiller.tasks.blog.model.dto;

import jakarta.validation.constraints.NotBlank;

public record NewCommentDto(@NotBlank String author,@NotBlank String content) {
}
