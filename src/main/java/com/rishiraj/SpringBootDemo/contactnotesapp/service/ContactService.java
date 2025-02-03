package com.rishiraj.SpringBootDemo.contactnotesapp.service;

import com.rishiraj.SpringBootDemo.contactnotesapp.data.ContactRepository;
import com.rishiraj.SpringBootDemo.contactnotesapp.data.domainobjects.Contact;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> getContacts(){
        return contactRepository.findAll();
    }

    public Contact saveContact(Contact contact){
        return contactRepository.save(contact);
    }

}
