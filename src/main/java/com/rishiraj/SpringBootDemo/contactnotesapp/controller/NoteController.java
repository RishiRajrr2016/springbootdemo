package com.rishiraj.SpringBootDemo.contactnotesapp.controller;

import com.rishiraj.SpringBootDemo.contactnotesapp.data.domainobjects.Note;
import com.rishiraj.SpringBootDemo.contactnotesapp.service.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public List<Note> getNotes() {
        return noteService.getAllNotes();
    }

    @PostMapping
    public Note createNote(@RequestBody Note note){
        return noteService.saveNote(note);
    }
}
