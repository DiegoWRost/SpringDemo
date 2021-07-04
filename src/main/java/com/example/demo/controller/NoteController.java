package com.example.demo.controller;

import com.example.demo.model.Note;
import com.example.demo.model.Person;
import com.example.demo.service.NoteService;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;
    private final PersonService personService;

    @Autowired
    public NoteController(NoteService noteService, PersonService personService) {
        this.noteService = noteService;
        this.personService = personService;
    }

    @GetMapping
    public String getNotes (@RequestParam("personId") long personId, Model model){
        Person person = personService.findById(personId);
        List<Note> notes = noteService.findByOwner(person);
        model.addAttribute("notes", notes);
        model.addAttribute("personId", personId);
        model.addAttribute("owner", person);
        return "notes/notes";
    }

    @GetMapping("/add")
    public String addNote(@RequestParam("personId") Long personId, Model model) {
        Person person = personService.findById(personId);
        Note note = new Note();
        note.setOwner(person);
        model.addAttribute("note", note);
        return "notes/note-form";
    }

    @GetMapping("/update")
    public String updateNote(@RequestParam("noteId") long noteId, Model model) {
        Note note = noteService.findById(noteId);
        model.addAttribute("note", note);
        return "notes/note-form";
    }

    @PostMapping("/save")
    public String saveNote(@RequestParam("personId") Long personId, @ModelAttribute("note") Note note) {
        Person person = personService.findById(personId);
        note.setOwner(person);
        noteService.save(note);
        return "redirect:/notes/?personId=" + personId;
    }

    @GetMapping("/delete")
    public String deleteNote(@RequestParam("noteId") long noteId, @RequestParam("personId") long personId) {
        noteService.deleteById(noteId);
        return "redirect:/notes/?personId=" + personId;
    }
}