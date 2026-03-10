package tech.ada.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import tech.ada.dto.CourseRequestDTO;
import tech.ada.model.Course;

import java.util.List;

@ApplicationScoped
public class CourseService {

    public List<Course> getCourses() {
        return Course.findAll().list();
    }
    public Course getCourseById(Long id) {
        Course course = Course.findById(id);
        if (course == null) {
            throw new EntityNotFoundException("Course with id " + id + "was not found");
        }
        return course;
    }

    public Course createCourse(@Valid CourseRequestDTO dto) {
        Course course = new Course(dto.name());
        course.persist();
        return course;

    }
}
