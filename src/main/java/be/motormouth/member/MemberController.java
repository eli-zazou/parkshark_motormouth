package be.motormouth.member;

import be.motormouth.member.dto.CreateMemberDto;
import be.motormouth.member.dto.MemberDto;
import be.motormouth.member.dto.MemberDtoSpecificFields;
import be.motormouth.member.entities.MembershipLevel;
import be.motormouth.member.services.MemberMapper;
import be.motormouth.member.services.MemberService;
import be.motormouth.security.SecurityService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestHeader;

import java.util.List;

import static be.motormouth.security.Feature.VIEW_ALL_MEMBERS;
import static be.motormouth.security.Feature.VIEW_A_MEMBER;

@Path("/members")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MemberController {
    @Inject
    MemberService memberService;

    @Inject
    SecurityService securityService;

    @GET
    public List<MemberDto> getAllMembers(@RestHeader String authorization){
       securityService.validateAuthorization(authorization, VIEW_ALL_MEMBERS);
        return MemberMapper.toDto(memberService.getAllMembers());
    }

    @GET
    @ResponseStatus(200)
    @Path("/{id}")
    public MemberDto getMemberById (@RestHeader String authorization, @PathParam("id") Long id){
        securityService.validateAuthorization(authorization, VIEW_A_MEMBER);
        return MemberMapper.toDto(memberService.getMemberById(id));
    }

    @POST
    @ResponseStatus(201)
    public MemberDto createMember(CreateMemberDto createMemberDto){
        return MemberMapper.toDto(memberService.createMember(createMemberDto));
    }

    // todo can we update our membership level??

    @GET
    @Path("/specificFields")
    public List<MemberDtoSpecificFields> getAllMembersSpecificFields(@RestHeader String authorization){
        securityService.validateAuthorization(authorization, VIEW_ALL_MEMBERS);
        return memberService.getAllMembersSpecificFields();
    }

}
