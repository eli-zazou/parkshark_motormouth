package be.motormouth.member;

import be.motormouth.member.dto.CreateMemberDto;
import be.motormouth.member.dto.MemberDto;
import be.motormouth.member.entities.MembershipLevel;
import be.motormouth.member.services.MemberService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/members")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MemberController {

    private final MemberService memberService;

    @Inject
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }


    @GET
    public List<MemberDto> getAllMembers(){
        return memberService.getAllMembers();
    }

    @Path("{id}")
    @GET
    public MemberDto getMemberById (@PathParam("id") String id){
        return memberService.getMemberById(id);
    }

    @POST
    public String registerMember(CreateMemberDto createMemberDto){
        return memberService.createMember(createMemberDto);
    }

    @PATCH
    public void setMembershipLevel(MembershipLevel membershipLevel){

    }
}
