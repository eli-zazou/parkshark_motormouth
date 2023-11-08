package be.motormouth.member.services;

import be.motormouth.member.MemberPanacheRepository;
import be.motormouth.member.dto.CreateMemberDto;
import be.motormouth.member.entities.Member;
import be.motormouth.member.entities.MembershipLevel;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@ApplicationScoped
@Transactional
public class MemberService {

    private final MemberPanacheRepository memberRepository;

    private final Logger logger = Logger.getLogger(MemberService.class);
    private String errorMessage;

    public MemberService(MemberPanacheRepository memberPanacheRepository){
        this.memberRepository = memberPanacheRepository;
    }

    public List<Member> getAllMembers(){
        return memberRepository.getAllMembers();
    }

    public Member getMemberById(Long id) {
        return memberRepository.getMemberById(id)
                .orElseThrow(()-> new NotFoundException(Response.status(Response.Status.NOT_FOUND).entity("Member with: " + id + " not Found").build()));
    }

    public Member createMember(CreateMemberDto createMemberDto) throws IllegalArgumentException {
        if (createMemberDto == null) {
            errorMessage = "Member to create not provided";
            logger.info(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        if(createMemberDto.getFirstName()== null || createMemberDto.getFirstName().isEmpty()){
            errorMessage= "Firstname not provided.";
            logger.info(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        if(createMemberDto.getLastName() == null || createMemberDto.getLastName().isEmpty()){
            errorMessage="Lastname not provided.";
            logger.info(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        if (createMemberDto.getLicensePlate() == null){
            errorMessage="Licence Plate not provided.";
            logger.info(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        if (createMemberDto.getAddress() == null){
            errorMessage = "Address not provided.";
            logger.info(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        if (createMemberDto.getPhoneNumber() == null){
            errorMessage = "Phone Number not provided.";
            logger.info(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        if( createMemberDto.getMembershipLevel() == null){
            createMemberDto.setMembershipLevel(MembershipLevel.BRONZE);
        }

        try{
            validateEmail(createMemberDto.getEmailAddress());
        }catch (IllegalArgumentException exception){
            errorMessage = "Email validation error: " + exception.getMessage();
            logger.info(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        try {
            MembershipLevel.valueOf(String.valueOf(createMemberDto.getMembershipLevel()));
        }catch (IllegalArgumentException exception){
            errorMessage = "Invalid Membership Level provided.";
            logger.info(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        Member memberToRegister = MemberMapper.toEntity(createMemberDto);
        memberRepository.createMember( memberToRegister);
        logger.info("Member created successfully");

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
            errorMessage= "Email address cannot be empty or null.";
            logger.info(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        List<Member>  allMembers = memberRepository.getAllMembers();
        Optional<Member> result = allMembers.stream()
                .filter(e -> e.getEmailAddress().equalsIgnoreCase(email))
                .findFirst();
        if (result.isPresent()) {
            errorMessage="Email " + email + " not unique";
            logger.info(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        if (!isValidEmail(email)) {
            errorMessage = "Email " + email + " not valid";
            logger.info(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        return email;
    }



}
