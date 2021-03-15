package com.example.demo.api;

import com.example.demo.models.Person;

import java.util.List;


public interface Api {

    int insertPerson(Person person);

    List<Person> getAllPersons();

    Person getPerson(String id);

    int deletePerson(String id);

    int updatePerson(String uid, Person person);

}
