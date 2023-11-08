package be.motormouth.exceptions;

import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

public class ExeptionHandler {
    @ServerExceptionMapper(value = {UnauthorizatedException.class, UnknownUserException.class, WrongPasswordException.class})
    public RestResponse<String> UnauthorisedException(RuntimeException ex) {
        return RestResponse.status(Response.Status.UNAUTHORIZED, ex.getMessage());
    }

    @ServerExceptionMapper(value = {UnknownDivisionException.class, UnknownParkingLotException.class, UnknownAllocationException.class})
    public RestResponse<String> NotFoundException(RuntimeException ex) {
        return RestResponse.status(Response.Status.NOT_FOUND, ex.getMessage());
    }

    @ServerExceptionMapper(value = {UnmatchedLicensePlateException.class, OutOfCapacityParkingLotException.class, UnmatchedMemberException.class, InactiveAllocationException.class})
    public RestResponse<String> forbiddenException(RuntimeException ex) {
        return RestResponse.status(Response.Status.FORBIDDEN, ex.getMessage());
    }

    @ServerExceptionMapper(value = {IllegalArgumentException.class})
    public RestResponse<String> badRequestException(RuntimeException ex) {
        return RestResponse.status(Response.Status.BAD_REQUEST, ex.getMessage());
    }
}
