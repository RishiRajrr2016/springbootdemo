package com.rishiraj.SpringBootDemo.contactnotesapp.data.domainobjects;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContactTest {
    @Test
    void testContactConstructorAndGetters() {
    Contact contact = new Contact();
    contact.setId(1L);
    contact.setName("John Doe");
    contact.setEmail("john@example.com");
    contact.setPhone("1234567890");
    assertEquals(1L, contact.getId());
    assertEquals("John Doe", contact.getName());
    assertEquals("john@example.com", contact.getEmail());
    assertEquals("1234567890", contact.getPhone());
    }

    @Test
    void testContactSetters() {
        Contact contact = new Contact();
        contact.setId(2L);
        contact.setName("Jane Smith");
        contact.setEmail("jane@example.com");
        contact.setPhone("0987654321");
        assertEquals(2L, contact.getId());
        assertEquals("Jane Smith", contact.getName());
        assertEquals("jane@example.com", contact.getEmail());
        assertEquals("0987654321", contact.getPhone());
    }
}
