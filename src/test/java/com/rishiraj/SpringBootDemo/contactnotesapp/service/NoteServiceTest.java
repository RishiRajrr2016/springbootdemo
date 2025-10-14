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
    Note note1 = new Note();
    note1.setId(1L);
    note1.setTitle("Title1");
    note1.setContent("Content1");
    Note note2 = new Note();
    note2.setId(2L);
    note2.setTitle("Title2");
    note2.setContent("Content2");
    List<Note> notes = Arrays.asList(note1, note2);
        when(noteRepository.findAll()).thenReturn(notes);
        List<Note> result = noteService.getAllNotes();
        assertEquals(2, result.size());
        assertEquals("Title1", result.get(0).getTitle());
    }

    @Test
    void testSaveNote() {
    Note note = new Note();
    note.setTitle("Title1");
    note.setContent("Content1");
    Note savedNote = new Note();
    savedNote.setId(1L);
    savedNote.setTitle("Title1");
    savedNote.setContent("Content1");
    when(noteRepository.save(note)).thenReturn(savedNote);
    Note result = noteService.saveNote(note);
    assertNotNull(result.getId());
    assertEquals("Title1", result.getTitle());
    }
}
