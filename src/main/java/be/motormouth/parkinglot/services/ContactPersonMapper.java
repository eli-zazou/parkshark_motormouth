package be.motormouth.parkinglot.services;

import be.motormouth.parkinglot.dtos.ContactPersonDto;
import be.motormouth.parkinglot.dtos.CreateContactPersonDto;
import be.motormouth.parkinglot.entities.ContactPerson;

public class ContactPersonMapper {
    public static ContactPerson toEntity(CreateContactPersonDto createContactPersonDto){
        ContactPerson contactPerson = new ContactPerson(createContactPersonDto.phoneNumber(),
                createContactPersonDto.mobilePhoneNumber(),
                createContactPersonDto.email(),
                createContactPersonDto.address());
//        ContactPerson contactPerson = new ContactPerson();
//        contactPerson.setPhoneNumber(createContactPersonDto.phoneNumber());
//        contactPerson.setMobilePhoneNumber(createContactPersonDto.mobilePhoneNumber());
//        contactPerson.setEmail(createContactPersonDto.email());
//        contactPerson.setAddress(createContactPersonDto.address());
        return contactPerson;
    }

    public static ContactPersonDto toDto(ContactPerson contactPerson){
        return new ContactPersonDto(contactPerson.getId(),
                contactPerson.getPhoneNumber(),
                contactPerson.getMobilePhoneNumber(),
                contactPerson.getEmail(),
                contactPerson.getAddress());
    }
}
