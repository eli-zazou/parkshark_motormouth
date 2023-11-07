package be.motormouth.member;

import be.motormouth.member.dto.CreateMemberDto;
import be.motormouth.member.dto.MemberDto;
import be.motormouth.member.entities.MembershipLevel;
import be.motormouth.member.services.MemberMapper;
import be.motormouth.member.services.MemberService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.ResponseStatus;

import java.util.List;

@Path("/members")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MemberController {
    @Inject
    MemberService memberService;

    @GET
    public List<MemberDto> getAllMembers(){
        return MemberMapper.toDto(memberService.getAllMembers());
    }

    @Path("{id}")
    @GET
    public MemberDto getMemberById (@PathParam("id") String id){
        return memberService.getMemberById(id);
    }

    @POST
    @ResponseStatus(201)
    public MemberDto createMember(CreateMemberDto createMemberDto){
        return MemberMapper.toDto(memberService.createMember(createMemberDto));
    }

    @PATCH
    public void setMembershipLevel(MembershipLevel membershipLevel){

    }
}
