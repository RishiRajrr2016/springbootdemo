package com.rishiraj.SpringBootDemo.contactnotesapp.data;

import com.rishiraj.SpringBootDemo.contactnotesapp.data.domainobjects.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
