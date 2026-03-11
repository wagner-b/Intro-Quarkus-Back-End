package tech.ada.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import tech.ada.dto.CourseResponseDTO;
import tech.ada.model.Lesson;
import tech.ada.service.LessonService;

import java.util.List;

@Path("/courses")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LessonResource {
    @Inject
    LessonService service;

    @GET
    @Path("/{courseId}/lessons")
    public Response getLessonsByCourseId(
            @PathParam("courseId") Long courseId
    ) {
        List<Lesson> lessons = service.getLessonsByCourseId(courseId);
        List<CourseResponseDTO> payload = lessons.stream()
                .map((Lesson l) ->
                        new CourseResponseDTO(l.getId(), l.getName()))
                .toList();
        return Response.ok().build();
    }
}
