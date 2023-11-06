package be.motormouth.member;

import be.motormouth.member.entities.Member;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.List;

public class MemberPanacheRepository implements PanacheRepository<Member> {

    public List<Member> getAllMembers(){
        return findAll().list();
    }

}
