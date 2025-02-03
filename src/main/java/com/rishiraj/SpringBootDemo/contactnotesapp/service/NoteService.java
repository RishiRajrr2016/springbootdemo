package com.rishiraj.SpringBootDemo.contactnotesapp.service;

import com.rishiraj.SpringBootDemo.contactnotesapp.data.NoteRepository;
import com.rishiraj.SpringBootDemo.contactnotesapp.data.domainobjects.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }
}
