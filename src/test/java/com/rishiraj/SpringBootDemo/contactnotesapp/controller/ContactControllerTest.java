package com.rishiraj.SpringBootDemo.contactnotesapp.controller;

import com.rishiraj.SpringBootDemo.contactnotesapp.data.domainobjects.Contact;
import com.rishiraj.SpringBootDemo.contactnotesapp.service.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ContactControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ContactService contactService;

    @InjectMocks
    private ContactController contactController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(contactController).build();
    }

    @Test
    void testGetContacts() throws Exception {
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
        when(contactService.getContacts()).thenReturn(contacts);

        mockMvc.perform(get("/api/contacts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[1].email").value("jane@example.com"));
    }

    @Test
    void testCreateContact() throws Exception {
    Contact savedContact = new Contact();
    savedContact.setId(1L);
    savedContact.setName("John Doe");
    savedContact.setEmail("john@example.com");
    savedContact.setPhone("1234567890");
        when(contactService.saveContact(any(Contact.class))).thenReturn(savedContact);

        String contactJson = "{\"name\":\"John Doe\",\"email\":\"john@example.com\",\"phone\":\"1234567890\"}";

        mockMvc.perform(post("/api/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(contactJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }
}
