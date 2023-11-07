package be.motormouth.member.services;

import be.motormouth.member.dto.CreateMemberDto;
import be.motormouth.member.dto.MemberDto;
import be.motormouth.member.entities.Member;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class MemberMapper {

    public static Member toEntity(CreateMemberDto createMemberDto) {
        return new Member(createMemberDto.getFirstName(),
                createMemberDto.getLastName(),
                createMemberDto.getPhoneNumber(),
                createMemberDto.getEmailAddress(),
                createMemberDto.getAddress(),
                createMemberDto.getLicensePlate(),
                createMemberDto.getMembershipLevel(),
                LocalDate.now());
    }

    public static MemberDto toDto (Member member){
        return new MemberDto(member.getId(),
                member.getFirstName(),
                member.getLastName(),
                member.getPhoneNumber(),
                member.getEmailAddress(),
                member.getAddress(),
                member.getLicencePlate().getLicensePlateNumber(),
                member.getRegistrationDate(),
                member.getMembershipLevel());
    }

    public static List<MemberDto> toDto(List<Member> members){
        return members.stream().map((MemberMapper::toDto))
                .collect(Collectors.toList());
    }


}
