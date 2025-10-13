package com.rishiraj.SpringBootDemo.contactnotesapp.data.domainobjects;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NoteTest {
    @Test
    void testNoteConstructorAndGetters() {
        Note note = new Note(1L, "Title1", "Content1");
        assertEquals(1L, note.getId());
        assertEquals("Title1", note.getTitle());
        assertEquals("Content1", note.getContent());
    }

    @Test
    void testNoteSetters() {
        Note note = new Note();
        note.setId(2L);
        note.setTitle("Title2");
        note.setContent("Content2");
        assertEquals(2L, note.getId());
        assertEquals("Title2", note.getTitle());
        assertEquals("Content2", note.getContent());
    }
}
