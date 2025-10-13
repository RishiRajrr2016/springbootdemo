package com.rishiraj.SpringBootDemo.contactnotesapp.service;

import com.rishiraj.SpringBootDemo.contactnotesapp.data.NoteRepository;
import com.rishiraj.SpringBootDemo.contactnotesapp.data.domainobjects.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NoteServiceTest {
    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService noteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllNotes() {
        List<Note> notes = Arrays.asList(
                new Note(1L, "Title1", "Content1"),
                new Note(2L, "Title2", "Content2")
        );
        when(noteRepository.findAll()).thenReturn(notes);
        List<Note> result = noteService.getAllNotes();
        assertEquals(2, result.size());
        assertEquals("Title1", result.get(0).getTitle());
    }

    @Test
    void testSaveNote() {
        Note note = new Note(null, "Title1", "Content1");
        Note savedNote = new Note(1L, "Title1", "Content1");
        when(noteRepository.save(note)).thenReturn(savedNote);
        Note result = noteService.saveNote(note);
        assertNotNull(result.getId());
        assertEquals("Title1", result.getTitle());
    }
}
