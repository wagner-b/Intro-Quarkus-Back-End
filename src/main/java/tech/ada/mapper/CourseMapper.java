package tech.ada.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import tech.ada.dto.CourseResponseDTO;
import tech.ada.dto.LessonResponseDTO;
import tech.ada.model.Course;
import tech.ada.model.Lesson;

import java.util.List;

@ApplicationScoped
public class CourseMapper {

    public CourseResponseDTO CourseToDTO(Course course, List<Lesson> lessons) {

        List<LessonResponseDTO> lessonDTOs = lessons.stream()
                .map((Lesson l) -> new LessonResponseDTO(l.getId(), l.getName()))
                .toList();

        return new CourseResponseDTO(
                course.getId(), course.getName(),
                lessonDTOs
        );
    }
}
