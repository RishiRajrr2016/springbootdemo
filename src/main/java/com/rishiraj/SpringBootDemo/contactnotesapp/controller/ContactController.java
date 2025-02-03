package com.rishiraj.SpringBootDemo.contactnotesapp.controller;

import com.rishiraj.SpringBootDemo.contactnotesapp.data.domainobjects.Contact;
import com.rishiraj.SpringBootDemo.contactnotesapp.service.ContactService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public List<Contact> getContacts() {
        return contactService.getContacts();
    }

    @PostMapping
    public Contact createContact(@RequestBody Contact contact){
        return contactService.saveContact(contact);
    }
}
