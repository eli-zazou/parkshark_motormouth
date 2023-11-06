package be.motormouth.member;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/members")
public class MemberController {

    @GET
    public String test(){
        return "test";
    }

}
