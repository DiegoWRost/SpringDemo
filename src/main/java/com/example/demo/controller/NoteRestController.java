package com.example.demo.controller;

import com.example.demo.model.Note;
import com.example.demo.model.Person;
import com.example.demo.service.NoteService;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/notes")
public class NoteRestController {

    private final NoteService noteService;
    private final PersonService personService;

    @Autowired
    public NoteRestController(NoteService noteService, PersonService personService) {
        this.noteService = noteService;
        this.personService = personService;
    }

    @GetMapping("{personId}")
    public List<Note> getNotes(@PathVariable("personId") Long personId){
        Person person = personService.findById(personId);
        return noteService.findByOwner(person);
    }

    @GetMapping ("{personId}/{noteId}")
    public Note getNote(@PathVariable("personId") Long personId, @PathVariable("noteId") Long noteId){
        Person person = personService.findById(personId);
        return noteService.findByOwnerAndNoteId(person, noteId);
    }

    @PostMapping("{personId}")
    public void addNote (@PathVariable("personId") Long personId, @RequestBody Note note) {
        Person person = personService.findById(personId);
        note.setOwner(person);
        noteService.save(note);
    }

    @PutMapping ("{noteId}")
    public void updateNote (@RequestBody Note note, @PathVariable Long noteId) {
        noteService.update(noteId, note);
    }

    @DeleteMapping ("{noteId}")
    public void deleteNote (@PathVariable Long noteId) {
        noteService.deleteById(noteId);
    }
}