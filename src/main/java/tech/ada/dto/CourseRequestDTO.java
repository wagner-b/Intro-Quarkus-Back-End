package tech.ada.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CourseRequestDTO (
        @NotNull(message = "Name must not be null")
        @NotBlank(message = "Name must not be blank")
        @Size(min = 3, message = "Name must have at least 3 characters")
        String name
) {
}
