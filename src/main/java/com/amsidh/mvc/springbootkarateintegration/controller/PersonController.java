package com.amsidh.mvc.springbootkarateintegration.controller;

import com.amsidh.mvc.springbootkarateintegration.exception.BadRequestException;
import com.amsidh.mvc.springbootkarateintegration.exception.NotFoundException;
import com.amsidh.mvc.springbootkarateintegration.model.Person;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final static Set<Person> persons = new HashSet<>();

    static {
        persons.add(new Person(1, "Amsidh Lokhande", "amsidh@gmail.com"));
        persons.add(new Person(2, "Anjali Lokhande", "anjali@gmail.com"));
        persons.add(new Person(3, "Adithi Lokhande", "adithi@gmail.com"));
        persons.add(new Person(4, "Aditya Lokhande", "aditya@gmail.com"));
    }

    @GetMapping
    public Set<Person> getAllPerson() {
        return persons;
    }

    @GetMapping(value = "/{personId}")
    public Person getPersonById(@PathVariable(name = "personId") Integer personId) {
        return persons.parallelStream().filter(person -> person.getId().equals(personId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Person with personId " + personId + " not found."));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person addPerson(@RequestBody Person person) {
        return persons.parallelStream().filter(p -> p.getId().equals(person.getId())).findFirst().orElseGet(() -> {
            persons.add(person);
            return person;
        });
    }

    @PutMapping(value = "/{personId}")
    public Person updatePerson(@PathVariable(name = "personId") Integer personId, @RequestBody Person person) {
        return persons.parallelStream().filter(p -> p.getId().equals(personId)).findFirst().map(p -> {
            Optional.ofNullable(person.getName()).ifPresent(p::setName);
            Optional.ofNullable(person.getEmail()).ifPresent(p::setEmail);
            return p;
        }).orElseThrow(() -> new BadRequestException("Unable to update the person with personId " + personId));
    }
}
