package com.example.demo.service;

import com.example.demo.dataaccess.PersonRepository;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(long personId) {
        return personRepository.findById(personId).orElseThrow(() ->
                new IllegalStateException("Person with id " + personId + " does not exist"));
    }

    public void save(Person person) {
        personRepository.save(person);
    }

    public void deleteById(long personId) {
        personRepository.deleteById(personId);
    }

    public void update(Long personId, Person person) {
        Person personToUpdate = findById(personId);
        personToUpdate.setActive(person.isActive());
        personToUpdate.setFirstName(person.getFirstName());
        personToUpdate.setLastName(person.getLastName());
        personToUpdate.setPassword(person.getPassword());
        save(personToUpdate);
    }
}