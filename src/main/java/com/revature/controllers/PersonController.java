package com.revature.controllers;

import com.revature.models.Person;
import com.revature.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getAllPeople(){
        return personService.getAllPeople();
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable("id") int id){
        return personService.getPersonById(id);
    }

    @PutMapping("/{id}")
    public Person updatePerson(@RequestBody Person person, @PathVariable("id") int id){
        person.setId(id);
        return personService.updatePerson(person);
    }

    @DeleteMapping("/{id}")
    public boolean deletePerson(@PathVariable("id")int id){
        return personService.deletePersonById(id);
    }

    @PostMapping
    public Person addPerson(@RequestBody Person person){
        return personService.addPerson(person);
    }



}
