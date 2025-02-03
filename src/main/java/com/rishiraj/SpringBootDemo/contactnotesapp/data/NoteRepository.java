package com.rishiraj.SpringBootDemo.contactnotesapp.data;

import com.rishiraj.SpringBootDemo.contactnotesapp.data.domainobjects.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}