package be.motormouth.member;

import be.motormouth.member.entities.Address;
import be.motormouth.member.entities.LicensePlate;
import be.motormouth.member.entities.Member;
import be.motormouth.member.entities.MembershipLevel;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
class MemberPanacheRepositoryTest {
    @Inject
    MemberPanacheRepository memberRepository;

//    @BeforeEach
//    public void clearDatabase() {
//        memberRepository.deleteAll();
//    }

    @Test
    void getAllMembers() {

        List<Member> members = memberRepository.getAllMembers();

        assertEquals(3, members.size());
        //expected 4 after creating a new member
    }

    @Test
    void getMemberById() {

        Optional<Member> retrievedMember = memberRepository.getMemberById(1L);

        assertTrue(retrievedMember.isPresent());
        assertEquals("Zineb", retrievedMember.get().getFirstName());
    }


    @Test
    void createMember() {

        Member newMember = new Member("Carmen", "Lastname", "0452635241", "carmen@email.be",
                new Address("EuroStreet", "11", 3000, "Leuven"), new LicensePlate("1-HUY528","NL"), MembershipLevel.SILVER, LocalDate.now());

        memberRepository.createMember(newMember);

        Optional<Member> getNewMember = memberRepository.getMemberById(newMember.getId());

        assertTrue(getNewMember.isPresent());
        assertEquals("Carmen", getNewMember.get().getFirstName());

        memberRepository.delete(newMember);

    }






}