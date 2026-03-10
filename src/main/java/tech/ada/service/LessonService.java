package tech.ada.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import tech.ada.dto.CreateLessonRequestDTO;
import tech.ada.model.Course;
import tech.ada.model.Lesson;

import java.util.List;

@ApplicationScoped
public class LessonService {

    public List<Lesson> getLessonsByCourseId(Long id) {
        Course course = Course.findById(id);
        if (course == null) {
            throw new EntityNotFoundException("Course with id " + id + " not found");
        }
        return course.getLessons();
    }

    @Transactional
    public Lesson createLesson(Long id,
                               @Valid CreateLessonRequestDTO dto) {
        Course course = Course.findById(id);
        if (course == null) {
            throw new EntityNotFoundException("Course with id " + id + " not found");
        }
        Lesson lesson = new Lesson(dto.name());
        course.createLesson(lesson);
        lesson.persist();
        return lesson;
    }
}
