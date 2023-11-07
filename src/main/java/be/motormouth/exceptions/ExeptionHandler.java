package be.motormouth.exceptions;

import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

public class ExeptionHandler {
    @ServerExceptionMapper(value = {UnauthorizatedException.class, UnknownUserException.class, WrongPasswordException.class})
    public RestResponse<String> UnauthorisedException(RuntimeException ex) {
        return RestResponse.status(Response.Status.UNAUTHORIZED, ex.getMessage());
    }

    @ServerExceptionMapper(value = {UnknownDivisionException.class, UnknownParkingLotException.class})
    public RestResponse<String> NotFoundException(RuntimeException ex) {
        return RestResponse.status(Response.Status.NOT_FOUND, ex.getMessage());
    }

    @ServerExceptionMapper(value = {UnmatchedLicensePlateException.class, OutOfCapacityParkingLotException.class})
    public RestResponse<String> forbiddenException(RuntimeException ex) {
        return RestResponse.status(Response.Status.FORBIDDEN, ex.getMessage());
    }
}
