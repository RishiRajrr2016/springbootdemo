package com.rishiraj.SpringBootDemo.contactnotesapp.controller;

import com.rishiraj.SpringBootDemo.contactnotesapp.data.domainobjects.Note;
import com.rishiraj.SpringBootDemo.contactnotesapp.service.NoteService;
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

class NoteControllerTest {
    private MockMvc mockMvc;

    @Mock
    private NoteService noteService;

    @InjectMocks
    private NoteController noteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(noteController).build();
    }

    @Test
    void testGetNotes() throws Exception {
    Note note1 = new Note();
    note1.setId(1L);
    note1.setTitle("Title1");
    note1.setContent("Content1");
    Note note2 = new Note();
    note2.setId(2L);
    note2.setTitle("Title2");
    note2.setContent("Content2");
    List<Note> notes = Arrays.asList(note1, note2);
        when(noteService.getAllNotes()).thenReturn(notes);

        mockMvc.perform(get("/api/notes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Title1"))
                .andExpect(jsonPath("$[1].content").value("Content2"));
    }

    @Test
    void testCreateNote() throws Exception {
    Note savedNote = new Note();
    savedNote.setId(1L);
    savedNote.setTitle("Title1");
    savedNote.setContent("Content1");
        when(noteService.saveNote(any(Note.class))).thenReturn(savedNote);

        String noteJson = "{\"title\":\"Title1\",\"content\":\"Content1\"}";

        mockMvc.perform(post("/api/notes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(noteJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Title1"));
    }
}
