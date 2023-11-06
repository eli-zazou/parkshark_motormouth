package be.motormouth.member;

import be.motormouth.member.entities.Member;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
@ApplicationScoped
public class MemberPanacheRepository implements PanacheRepository<Member> {

    public List<Member> getAllMembers(){
        return findAll().list();
    }

}
