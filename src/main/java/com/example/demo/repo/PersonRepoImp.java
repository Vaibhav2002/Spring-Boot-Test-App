package com.example.demo.repo;

import com.example.demo.api.Api;
import com.example.demo.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("postgres")
public class PersonRepoImp implements Api {

    private final JdbcTemplate jdbcTemplate;
    private final String SELECT_ALL = "SELECT * FROM person";
    private final String GET_PERSON = "SELECT * FROM person WHERE id=?";
    private final String INSERT_PERSON = "INSERT INTO person (id,firstname,lastname) VALUES (?,?,?);";
    private final String DELETE_PERSON = "DELETE * FROM person WHERE id=?";
    private final String UPDATE_PERSON = "UPDATE person SET id=?,firstname=?,lastname=? WHERE id=?";


    @Autowired
    public PersonRepoImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int insertPerson(Person person) {
        return jdbcTemplate.update(INSERT_PERSON,
                person.getId(),
                person.getFirstName(),
                person.getLastName());
    }

    @Override
    public List<Person> getAllPersons() {
        return jdbcTemplate.query(SELECT_ALL, (resultSet, index) -> {
            String uid = resultSet.getString("id");
            String firstName = resultSet.getString("firstname");
            String lastName = resultSet.getString("lastname");
            return new Person(uid, firstName, lastName);
        });
    }

    @Override
    public Person getPerson(String uid) {
        return jdbcTemplate.queryForObject(GET_PERSON, (resultSet, index) -> {
            String id = resultSet.getString("id");
            String firstName = resultSet.getString("firstname");
            String lastName = resultSet.getString("lastname");
            return new Person(id, firstName, lastName);
        }, uid);
    }

    @Override
    public int deletePerson(String id) {
        return jdbcTemplate.update(DELETE_PERSON, id);
    }

    @Override
    public int updatePerson(String uid, Person person) {
        return jdbcTemplate.update(UPDATE_PERSON, person.getId(),
                person.getFirstName(),
                person.getLastName(),
                uid);
    }
}
