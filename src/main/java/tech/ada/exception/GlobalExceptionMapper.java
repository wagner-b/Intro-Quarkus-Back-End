package tech.ada.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.NotSupportedException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.Map;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {

        if (e instanceof ConstraintViolationException) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of(
                            "error", "Validation error",
                            "details", e.getMessage()
                    ))
                    .build();
        }

        if (e instanceof org.hibernate.exception.ConstraintViolationException) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of(
                            "error", "Database constraint violation"
                    ))
                    .build();
        }

        if (e instanceof EntityNotFoundException) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of(
                            "error", "Resource not found"
                    ))
                    .build();
        }

        if(e instanceof NotSupportedException) {
            return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE)
                    .entity(Map.of(
                            "error", "Unsupported Media Type",
                            "message", "Content-Type must be application/json"
                    ))
                    .build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(Map.of(
                        "error", "Unexpected error"
                ))
                .build();
    }
}