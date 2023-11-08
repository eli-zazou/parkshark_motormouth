package be.motormouth.security.users;

import be.motormouth.member.dto.CreateMemberDto;
import be.motormouth.member.entities.Member;
import com.google.common.hash.Hashing;
import jakarta.enterprise.context.ApplicationScoped;

import java.nio.charset.StandardCharsets;

@ApplicationScoped
public class UserMapper {
    public User toUser(CreateMemberDto createMemberDto, UserRole userRole, Member member){
        return new User(createMemberDto.getUsername().toLowerCase(), Hashing.sha256().hashString(createMemberDto.getPassword(), StandardCharsets.UTF_8).toString(), userRole, member);
    }
}
