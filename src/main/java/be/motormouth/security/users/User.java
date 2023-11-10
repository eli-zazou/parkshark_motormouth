package be.motormouth.security.users;

import be.motormouth.member.entities.Member;
import be.motormouth.security.Feature;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "USERS")
public class User {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", sequenceName = "users_seq", allocationSize = 1)
    private long id;
    @Column(name = "USER_ID")
    private String userId;
    @Column(name = "PASSWORD")
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    private UserRole userRole;
    @OneToOne
    @JoinColumn(name = "FK_MEMBER_ID")
    private Member member;

    public User() {
    }

    public User(String userId, String password, UserRole userRole, Member member) {
        this.userId = userId;
        this.password = password;
        this.userRole = userRole;
        this.member = member;
    }

    public User(long id, String userId, String password, UserRole userRole, Member member) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.userRole = userRole;
        this.member = member;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public Member getMember() {
        return member;
    }

    public boolean canHaveAccessTo(Feature feature) {
        return userRole.containsFeature(feature);
    }

    public boolean doesPasswordMatch(String password) {
        return this.password.equals(password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                ", member=" + member +
                '}';
    }
}
