package com.example.demo.service;

import com.example.demo.api.Api;
import com.example.demo.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {


    @Autowired
    @Qualifier("postgres")
    Api personRepo;


    public int insertPerson(Person person) {
        System.out.println(person.toString());
        return personRepo.insertPerson(person);
    }

    public List<Person> getAllPersons() {
        return personRepo.getAllPersons();
    }

    public Person getPerson(String uid) {
        return personRepo.getPerson(uid);
    }

    public int deletePerson(String uid) {
        return personRepo.deletePerson(uid);
    }

    public int updatePerson(String uid, Person person) {
        return personRepo.updatePerson(uid, person);
    }
}
