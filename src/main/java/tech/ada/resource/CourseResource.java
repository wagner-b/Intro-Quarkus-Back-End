package tech.ada.resource;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import tech.ada.dto.CourseRequestDTO;
import tech.ada.dto.CourseResponseDTO;
import tech.ada.model.Course;
import tech.ada.service.CourseService;

import java.net.URI;
import java.util.List;

@Path("/courses")
public class CourseResource {
    @Inject
    CourseService service;

    @GET
    public Response getCourses() {
        List<Course> courses = service.getCourses();

        List<CourseResponseDTO> response = courses.stream()
                .map((Course c) -> new CourseResponseDTO(c.getId(),
                                                         c.getName()))
                .toList();
        return Response.ok(response).build();
    }

    @POST
    @Transactional
    public Response createCourse(@Valid CourseRequestDTO dto) {
        Course course = service.createCourse(dto);

        URI location = URI.create("/courses/" + course.getId());
        CourseResponseDTO payload = new CourseResponseDTO(course.getId(),course.getName());

        return Response.created(location)
                .header("Content-Type", "application/json")
                .entity(payload)
                .build();
    }
}
