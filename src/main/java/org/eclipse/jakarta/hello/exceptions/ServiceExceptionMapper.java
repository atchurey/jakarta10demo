package org.eclipse.jakarta.hello.exceptions;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ServiceExceptionMapper implements ExceptionMapper<ServiceException> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(ServiceException ex) {

        BaseError baseError = new BaseError();
        baseError.setUrl(uriInfo.getAbsolutePath().getPath());
        baseError.setErrorCode(ex.getCode());
        baseError.setErrorMessage(ex.getMessage());
        baseError.setUserErrorMessage(ex.getUserMessage());


        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(baseError).build();

    }
}
