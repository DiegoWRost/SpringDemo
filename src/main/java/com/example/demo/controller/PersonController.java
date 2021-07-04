package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String getPeople(Model model){
        List<Person> people = personService.findAll();
        model.addAttribute("people", people);
        return "people/people";
    }

    @GetMapping("/add")
    public String addPerson(Model model) {
        Person person = new Person();
        model.addAttribute("person", person);
        return "people/person-form";
    }

    @GetMapping("/update")
    public String updatePerson(@RequestParam("personId") long id, Model model) {
        Person person = personService.findById(id);
        model.addAttribute("person", person);
        return "people/person-form";
    }

    @PostMapping("/save")
    public String savePerson(@ModelAttribute("person") Person person) {
        personService.save(person);
        // use a redirect to prevent duplicate submissions
        return "redirect:/people";
    }

    @GetMapping("/delete")
    public String deletePerson(@RequestParam("personId") long id) {
        personService.deleteById(id);
        return "redirect:/people";
    }
}