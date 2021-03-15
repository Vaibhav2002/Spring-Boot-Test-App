package com.example.demo.controller;

import com.example.demo.models.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/person")
public class PersonApiController {

    @Autowired
    PersonService personService;

    @PostMapping
    int insertPerson(@RequestBody Person person) {
        return personService.insertPerson(new Person(person.getFirstName(), person.getLastName()));
    }

    @GetMapping
    List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("{id}")
    Person getPerson(@PathVariable("id") String id) {
        return personService.getPerson(id);
    }

    @DeleteMapping("{id}")
    int deletePerson(@PathVariable("id") String id) {
        return personService.deletePerson(id);
    }

    @PutMapping("{id}")
    public int updatePerson(@PathVariable("id") String uid, @RequestBody Person person) {
        return personService.updatePerson(uid, person);
    }


}
