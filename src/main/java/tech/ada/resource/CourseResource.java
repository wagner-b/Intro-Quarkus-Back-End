package tech.ada.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
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
    @Produces(MediaType.APPLICATION_JSON)
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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
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
