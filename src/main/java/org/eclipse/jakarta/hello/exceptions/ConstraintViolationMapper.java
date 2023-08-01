package org.eclipse.jakarta.hello.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Provider
public class ConstraintViolationMapper implements ExceptionMapper<ConstraintViolationException> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(final ConstraintViolationException ex) {

        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

        Map<String, String> errors = new HashMap<>();

        for (final var constraint : constraintViolations) {

            String propertyPath = constraint.getPropertyPath().toString().split("\\.")[2];
            String message = constraint.getMessage();

            errors.put(propertyPath, message);
        }

        BaseError baseError = new BaseError();
        baseError.setUrl(uriInfo.getAbsolutePath().getPath());
        baseError.setErrorCode(100);
        baseError.setErrorMessage(errors.toString());
        baseError.setUserErrorMessage("");

        return Response.status(Response.Status.BAD_REQUEST).entity(baseError).build();
    }
}
