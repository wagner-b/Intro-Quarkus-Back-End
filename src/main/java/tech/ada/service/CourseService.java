package tech.ada.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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
            throw new EntityNotFoundException("Course with id " + id + " not found");
        }
        return course;
    }

    @Transactional
    public Course createCourse(@Valid CourseRequestDTO dto) {
        Course course = new Course(dto.name());
        course.persist();
        return course;

    }

    @Transactional
    public Course updateCourse(Long id, @Valid CourseRequestDTO dto) {
        Course course = Course.findById(id);
        if (course == null) {
            throw new EntityNotFoundException("Course with id " + id + " not found");
        }
        course.changeName(dto.name());
        return course;
    }

   @Transactional
   public Course deleteCourse(Long id, @Valid CourseRequestDTO dto) {
       Course course = Course.findById(id);
       if (course == null) {
           throw new EntityNotFoundException("Course with id " + id + " not found");
       }
       course.delete();
       return course;
   }
}
