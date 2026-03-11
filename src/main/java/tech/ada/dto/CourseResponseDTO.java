package tech.ada.dto;

import java.util.List;

public record CourseResponseDTO (
        Long id,
        String name,
        List<LessonResponseDTO> lessons
) {
}
