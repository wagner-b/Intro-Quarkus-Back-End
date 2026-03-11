package tech.ada.resource;

import jakarta.inject.Inject;
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
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CourseResource {
    @Inject
    CourseService service;

    @GET
    public Response getCourses() {
        List<Course> courses = service.getCourses();

        List<CourseResponseDTO> payload = courses.stream()
                .map((Course c) -> new CourseResponseDTO(c.getId(),
                                                         c.getName()))
                .toList();
        return Response.ok(payload).build();
    }

    @POST
    public Response createCourse(@Valid CourseRequestDTO dto) {
        Course course = service.createCourse(dto);

        URI location = URI.create("/courses/" + course.getId());
        CourseResponseDTO payload = new CourseResponseDTO(course.getId(),course.getName());

        return Response.created(location)
                .header("Content-Type", "application/json")
                .entity(payload)
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getCourseById(@PathParam("id") Long id) {
        Course course = service.getCourseById(id);
        CourseResponseDTO payload = new CourseResponseDTO(id, course.getName());
        return Response.ok(payload).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateCourse(@PathParam("id") Long id,
                                 @Valid CourseRequestDTO dto) {
        Course course = service.updateCourse(id, dto);
        CourseResponseDTO payload = new CourseResponseDTO(id, course.getName());
        return Response.ok(payload).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCourse(@PathParam("id") Long id,
                                 @Valid CourseRequestDTO dto) {
        service.deleteCourse(id, dto);
        return Response.noContent().build();
    }
}
