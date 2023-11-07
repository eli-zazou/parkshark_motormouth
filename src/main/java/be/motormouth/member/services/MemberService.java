package be.motormouth.member.services;


import be.motormouth.member.MemberPanacheRepository;
import be.motormouth.member.dto.CreateMemberDto;
import be.motormouth.member.dto.MemberDto;
import be.motormouth.member.entities.Member;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class MemberService {

    private final MemberPanacheRepository memberRepository;

    public MemberService(MemberPanacheRepository memberPanacheRepository){
        this.memberRepository = memberPanacheRepository;
    }

    public List<Member> getAllMembers(){
        return memberRepository.getAllMembers();
    }

    public MemberDto getMemberById(String id) {
        throw new RuntimeException("Not Implemented");
    }

    public Member createMember(CreateMemberDto createMemberDto) throws IllegalArgumentException {
        if (createMemberDto == null) {
            throw new IllegalArgumentException("Member to create not provided");
        }
        if(createMemberDto.firstName()== null || createMemberDto.firstName().isEmpty()){
            throw new IllegalArgumentException("Firstname not provided.");
        }
        if(createMemberDto.lastName() == null || createMemberDto.lastName().isEmpty()){
            throw new IllegalArgumentException("Lastname not provided.");
        }
        validateEmail(createMemberDto.emailAddress());
        if (createMemberDto.licensePlate() == null){
            throw new IllegalArgumentException("Licence Plate not provided.");
        }
        if (createMemberDto.address() == null){
            throw new IllegalArgumentException("Address not provided.");
        }
        if (createMemberDto.phoneNumber() == null){
            throw new IllegalArgumentException("Phone Number not provided.");
        }
        Member memberToRegister = MemberMapper.toEntity(createMemberDto);
        memberRepository.createMember( memberToRegister);
        return memberToRegister;
    }

    public boolean isValidEmail(String email) {
        String EMAIL_REGEX =
                "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public String validateEmail(String email) throws IllegalArgumentException {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email address cannot be empty or null.");
        }
        List<Member>  allMembers = memberRepository.getAllMembers();
        Optional<Member> result = allMembers.stream()
                .filter(e -> e.getEmailAddress().equalsIgnoreCase(email))
                .findFirst();
        if (result.isPresent()) {
            throw new IllegalArgumentException("Email " + email + " not unique");
        }
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Email " + email + " not valid");
        }
        return email;
    }



}
