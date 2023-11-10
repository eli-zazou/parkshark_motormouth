package be.motormouth.member.dto;

import be.motormouth.member.entities.MembershipLevel;

public record UpdateMembershipDto(MembershipLevel membershipLevel) {
}
