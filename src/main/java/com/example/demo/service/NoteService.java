package com.example.demo.service;

import com.example.demo.dataaccess.NoteRepository;
import com.example.demo.model.Note;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note findById(Long noteId) {
        return noteRepository.findById(noteId).orElseThrow(() ->
                new IllegalStateException("Could not find note with id " + noteId));
    }

    public List<Note> findByOwner(Person person) {
        return noteRepository.findByOwner(person).orElseThrow(() ->
                new IllegalStateException("Could not find any notes for person with id " + person.getPersonId()));
    }

    public Note findByOwnerAndNoteId(Person person, Long noteId) {
        return noteRepository.findByOwnerAndNoteId(person, noteId).orElseThrow(() ->
                new IllegalStateException("Could not find note with id " + noteId + " for person with id " + person.getPersonId()));
    }

    public void save(Note note) {
        noteRepository.save(note);
    }

    public void deleteById(long noteId) {
        noteRepository.deleteById(noteId);
    }

    public void update(Long noteId, Note note) {
        Note noteToUpdate = findById(noteId);
        noteToUpdate.setTitle(note.getTitle());
        noteToUpdate.setContents(note.getContents());
        save(noteToUpdate);
    }
}