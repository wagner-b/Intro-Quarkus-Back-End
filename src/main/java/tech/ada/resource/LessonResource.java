package tech.ada.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import tech.ada.dto.CourseResponseDTO;
import tech.ada.dto.CreateLessonRequestDTO;
import tech.ada.dto.LessonResponseDTO;
import tech.ada.model.Lesson;
import tech.ada.service.LessonService;

import java.net.URI;
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
        return Response.ok(payload).build();
    }

    @POST
    @Path("/{courseId}/lessons")
    public Response createLesson(
            @PathParam("courseId") Long courseId,
            CreateLessonRequestDTO dto
    ) {
        Lesson lesson = service.createLesson(courseId, dto);
        LessonResponseDTO payload = new LessonResponseDTO(
                lesson.getId(), lesson.getName()
        );
        URI location = URI.create("/courses/" + courseId + "/lessons");
        return Response.created(location)
                .header("Content-Type", "application/json")
                .entity(payload)
                .build();
    }

}
