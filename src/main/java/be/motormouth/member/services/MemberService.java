package be.motormouth.member.services;


import be.motormouth.member.MemberPanacheRepository;
import be.motormouth.member.dto.CreateMemberDto;
import be.motormouth.member.dto.MemberDto;
import be.motormouth.member.entities.Member;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class MemberService {

    private final MemberPanacheRepository memberRepository;

    public MemberService(MemberPanacheRepository memberPanacheRepository){
        this.memberRepository = memberPanacheRepository;
    }

    public List<MemberDto> getAllMembers(){
        throw new RuntimeException("Not Implemented");
//        return memberRepository.getAllMembers().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public MemberDto getMemberById(String id) {
        throw new RuntimeException("Not Implemented");
    }

    public String createMember(CreateMemberDto createMemberDto) {
        throw new RuntimeException("Not Implemented");
    }

    private MemberDto toDto (Member member){
        return new MemberDto(member.getId(),
                member.getFirstName(),
                member.getLastName(),
                member.getPhoneNumber(),
                member.getEmailAddress(),
                member.getAddress(),
                member.getLicencePlate(),
                member.getRegistrationDate(),
                member.getMembershipLevel());
    }

}
