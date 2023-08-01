package org.eclipse.jakarta.hello.exceptions;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@Provider
public class SQLIntegrityConstraintViolationExceptionMapper implements ExceptionMapper<SQLException> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(SQLException ex) {

        BaseError baseError = new BaseError();
        baseError.setUrl(uriInfo.getAbsolutePath().getPath());
        baseError.setErrorCode(1);
        baseError.setErrorMessage(ex.getMessage());
        baseError.setUserErrorMessage("");


        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(baseError).build();

    }
}
