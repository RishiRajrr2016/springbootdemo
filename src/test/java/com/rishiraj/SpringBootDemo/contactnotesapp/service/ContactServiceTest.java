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
        List<Contact> contacts = Arrays.asList(
                new Contact(1L, "John Doe", "john@example.com", "1234567890"),
                new Contact(2L, "Jane Smith", "jane@example.com", "0987654321")
        );
        when(contactRepository.findAll()).thenReturn(contacts);
        List<Contact> result = contactService.getContacts();
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
    }

    @Test
    void testSaveContact() {
        Contact contact = new Contact(null, "John Doe", "john@example.com", "1234567890");
        Contact savedContact = new Contact(1L, "John Doe", "john@example.com", "1234567890");
        when(contactRepository.save(contact)).thenReturn(savedContact);
        Contact result = contactService.saveContact(contact);
        assertNotNull(result.getId());
        assertEquals("John Doe", result.getName());
    }
}
