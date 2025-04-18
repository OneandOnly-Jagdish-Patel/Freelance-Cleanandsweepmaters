package ca.sheridancollege.pate5749.Repository;

import org.springframework.data.repository.CrudRepository;

import ca.sheridancollege.pate5749.Bean.Contact;

public interface ContactRepository extends CrudRepository<Contact, Long> {
	
}
