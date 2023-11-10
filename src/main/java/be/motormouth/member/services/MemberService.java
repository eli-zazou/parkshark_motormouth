package be.motormouth.member.services;

import be.motormouth.globalservices.validation.EmailValidator;
import be.motormouth.member.MemberPanacheRepository;
import be.motormouth.member.dto.CreateMemberDto;
import be.motormouth.member.dto.MemberDtoSpecificFields;
import be.motormouth.member.dto.UpdateMembershipDto;
import be.motormouth.member.entities.Member;
import be.motormouth.member.entities.MembershipLevel;

import be.motormouth.security.users.User;
import be.motormouth.security.users.UserMapper;
import be.motormouth.security.users.UserRepository;
import be.motormouth.security.users.UserRole;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@ApplicationScoped
@Transactional
public class MemberService {

    private final MemberPanacheRepository memberRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final Logger logger = Logger.getLogger(MemberService.class);
    private String errorMessage;

    public MemberService(MemberPanacheRepository memberPanacheRepository, UserRepository userRepository, UserMapper userMapper){
        this.memberRepository = memberPanacheRepository;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<Member> getAllMembers(){
        return memberRepository.getAllMembers();
    }

    public List<MemberDtoSpecificFields> getAllMembersSpecificFields(){
        List<Member> members= memberRepository.getAllMembers();
        return members.stream()
                .map(member -> new MemberDtoSpecificFields(
                        member.getId(),
                        member.getFirstName(),
                        member.getLastName(),
                        member.getLicencePlate().getLicensePlateNumber(),
                        member.getPhoneNumber(),
                        member.getEmailAddress(),
                        member.getRegistrationDate()
                )).collect(Collectors.toList());



    }

    public Member getMemberById(Long id) {
        return memberRepository.getMemberById(id)
                .orElseThrow(()-> new NotFoundException(Response.status(Response.Status.NOT_FOUND).entity("Member with: " + id + " not Found").build()));
    }

    public Member createMember(CreateMemberDto createMemberDto){

        validateInput(createMemberDto);

        Member memberToRegister = MemberMapper.toEntity(createMemberDto);
        memberRepository.createMember( memberToRegister);
        userRepository.addUser(userMapper.toUser(createMemberDto, UserRole.MEMBER, memberToRegister));
        logger.info("Member created successfully");

        return memberToRegister;
    }

    private String validateEmail(String email) throws IllegalArgumentException {
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
        if (!EmailValidator.isEmailValid(email)) {
            errorMessage = "Email " + email + " not valid";
            logger.info(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        return email;
    }

    private  void  validateInput(CreateMemberDto createMemberDto){
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
        validateMembershipLevel(createMemberDto.getMembershipLevel());
    }

    private void validateMembershipLevel(MembershipLevel membershipLevel) {
        try {
            MembershipLevel.valueOf(String.valueOf(membershipLevel));
        }catch (IllegalArgumentException exception){
            errorMessage = "Invalid Membership Level provided.";
            logger.info(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public String updateMembership(UpdateMembershipDto updateMembershipDto, Member member) {
        if( updateMembershipDto.membershipLevel() == null){
            errorMessage = "Membership level not provided";
            logger.info(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        validateMembershipLevel(updateMembershipDto.membershipLevel());
        Member memberToUpdate = getMemberById(member.getId());
        memberToUpdate.setMembershipLevel(updateMembershipDto.membershipLevel());
        return "Membership Level updated from " + member.getMembershipLevel() + " to " + memberToUpdate.getMembershipLevel();
    }
}
