package com.example.demo.dataaccess;

import com.example.demo.model.Note;
import com.example.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    Optional<List<Note>> findByOwner(Person owner);
    Optional<Note> findByOwnerAndNoteId(Person owner, Long noteId);
}
