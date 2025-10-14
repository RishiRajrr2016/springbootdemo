package com.rishiraj.SpringBootDemo.contactnotesapp.service;

import com.rishiraj.SpringBootDemo.contactnotesapp.data.ContactRepository;
import com.rishiraj.SpringBootDemo.contactnotesapp.data.domainobjects.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContactServiceTest {
    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactService contactService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetContacts() {
    Contact contact1 = new Contact();
    contact1.setId(1L);
    contact1.setName("John Doe");
    contact1.setEmail("john@example.com");
    contact1.setPhone("1234567890");
    Contact contact2 = new Contact();
    contact2.setId(2L);
    contact2.setName("Jane Smith");
    contact2.setEmail("jane@example.com");
    contact2.setPhone("0987654321");
    List<Contact> contacts = Arrays.asList(contact1, contact2);
        when(contactRepository.findAll()).thenReturn(contacts);
        List<Contact> result = contactService.getContacts();
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
    }

    @Test
    void testSaveContact() {
    Contact contact = new Contact();
    contact.setName("John Doe");
    contact.setEmail("john@example.com");
    contact.setPhone("1234567890");
    Contact savedContact = new Contact();
    savedContact.setId(1L);
    savedContact.setName("John Doe");
    savedContact.setEmail("john@example.com");
    savedContact.setPhone("1234567890");
    when(contactRepository.save(contact)).thenReturn(savedContact);
    Contact result = contactService.saveContact(contact);
    assertNotNull(result.getId());
    assertEquals("John Doe", result.getName());
    }
}
