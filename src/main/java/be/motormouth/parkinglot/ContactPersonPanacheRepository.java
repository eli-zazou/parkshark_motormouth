package be.motormouth.parkinglot;

import be.motormouth.parkinglot.entities.ContactPerson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ContactPersonPanacheRepository implements PanacheRepository<ContactPerson> {
    public ContactPerson createContactPerson(ContactPerson contactPerson){
        persist(contactPerson);
        return contactPerson;
    }
}
