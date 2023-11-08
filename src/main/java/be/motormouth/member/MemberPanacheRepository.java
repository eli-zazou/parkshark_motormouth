package be.motormouth.member;

import be.motormouth.member.dto.CreateMemberDto;
import be.motormouth.member.dto.MemberDto;
import be.motormouth.member.entities.Member;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class MemberPanacheRepository implements PanacheRepository<Member> {

    public List<Member> getAllMembers(){
        return findAll().list();
    }

    public void createMember(Member member){
         persist(member);
    }


    public Optional<Member> getMemberById(Long id) {
        return Optional.ofNullable(findById(id));
    }
}
