package tech.ada.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateLessonRequestDTO (
        @NotNull(message = "Name must not be null")
        @NotBlank(message = "Name must not be blank")
        String name
) {
}
