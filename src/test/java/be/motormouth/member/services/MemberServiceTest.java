package be.motormouth.member.services;

import be.motormouth.member.MemberPanacheRepository;
import be.motormouth.member.dto.CreateMemberDto;
import be.motormouth.member.dto.MemberDto;
import be.motormouth.member.entities.Address;
import be.motormouth.member.entities.LicensePlate;
import be.motormouth.member.entities.Member;
import be.motormouth.member.entities.MembershipLevel;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
class MemberServiceTest {
    @Inject
    MemberService memberService;

    @Inject
    MemberPanacheRepository memberRepository;

    @Test
    void test_getAllMembers() {

        List<Member> members = memberService.getAllMembers();

        assertEquals(3, members.size());
        //expected 4 after creating a new member
    }

    @Test
    public void test_GetMemberById() {

        Member retrievedMember = memberService.getMemberById(1L);

        assertEquals("Zineb", retrievedMember.getFirstName());
    }

    @Test
    public void test_GetMemberById_NotFound() {

        assertThrows(NotFoundException.class, () -> memberService.getMemberById(999L));
    }

    @Test
    void test_createMember() {

        CreateMemberDto newMemberDto = new CreateMemberDto("Carmen", "Lastname", "0452635241", "carmen@email.be",
                new Address("EuroStreet", "11", 3000, "Leuven"), new LicensePlate("1-HUY528","NL"),  LocalDate.now(), MembershipLevel.SILVER, "username", "password");

        memberService.createMember(newMemberDto);
        Member newMember = MemberMapper.toEntity(newMemberDto);

        Optional<Member> getNewMember = memberRepository.getMemberByEmail("carmen@email.be");


        assertTrue(getNewMember.isPresent());
        assertEquals("Carmen", getNewMember.get().getFirstName());

    }

    @Test
    public void test_CreateMember_InvalidInput(){
        CreateMemberDto createMemberDto = new CreateMemberDto("Carmen", "Lastname", "0452635241", "carmenemail.be",
                new Address("EuroStreet", "11", 3000, "Leuven"), new LicensePlate("1-HUY528","NL"),  LocalDate.now(), MembershipLevel.SILVER, "username", "password");

        assertThrows(IllegalArgumentException.class, () -> memberService.createMember(createMemberDto));

    }

}