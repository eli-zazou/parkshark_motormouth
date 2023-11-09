package be.motormouth.testConstants;

import be.motormouth.member.dto.CreateMemberDto;
import be.motormouth.member.entities.Address;
import be.motormouth.member.entities.LicensePlate;
import be.motormouth.member.entities.Member;
import be.motormouth.member.entities.MembershipLevel;
import be.motormouth.member.services.MemberMapper;

public class MemberTestConstants {
    public static final CreateMemberDto CREATE_MEMBER_DTO_SILVER = new CreateMemberDto(
            "Carmen",
            "Lastname",
            "0452635241",
            "carmen@email.be",
            new Address("EuroStreet", "11", 3000, "Leuven"),
            new LicensePlate("1-HUY528", "NL"),
            MembershipLevel.SILVER, "silveruser", "password");

    public static final Member MEMBER_SILVER = MemberMapper.toEntity(CREATE_MEMBER_DTO_SILVER);
    public static final CreateMemberDto CREATE_MEMBER_DTO_GOLD = new CreateMemberDto(
            "Mathieu",
            "Lastname",
            "0452635123",
            "mathieu@email.be",
            new Address("EuroStreet", "12", 3000, "Leuven"),
            new LicensePlate("1-HUY530", "NL"),
            MembershipLevel.SILVER, "golduser", "password");

    public static final Member MEMBER_GOLD = MemberMapper.toEntity(CREATE_MEMBER_DTO_GOLD);
}
