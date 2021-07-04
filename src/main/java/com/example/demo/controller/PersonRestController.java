package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/people")
public class PersonRestController {

    private final PersonService personService;

    @Autowired
    public PersonRestController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getPeople(){
        return personService.findAll();
    }

    @GetMapping ("{personId}")
    public Person getPerson(@PathVariable("personId") Long personId){
        return personService.findById(personId);
    }

    @PostMapping
    public void addPerson (@RequestBody Person person) {
        personService.save(person);
    }

    @PutMapping ("{personId}")
    public void updatePerson (@RequestBody Person person, @PathVariable Long personId) {
        personService.update(personId, person);
    }

    @DeleteMapping ("{personId}")
    public void deletePerson (@PathVariable Long personId) {
        personService.deleteById(personId);
    }
}
