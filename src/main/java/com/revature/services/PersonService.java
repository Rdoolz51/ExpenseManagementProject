package com.revature.services;

import com.revature.Exceptions.PersonNotFoundException;
import com.revature.daos.RoleDAO;
import com.revature.daos.PersonDAO;
import com.revature.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonDAO personDAO;

    private final RoleDAO roleDAO;

    @Autowired
    public PersonService(PersonDAO personDAO, RoleDAO roleDAO) {
        this.personDAO = personDAO;
        this.roleDAO = roleDAO;
    }

    public List<Person> getAllPeople() {
        return personDAO.findAll();

    }

    public Person getPersonById(int id) {
        return personDAO.findById(id).orElseThrow(() -> new PersonNotFoundException("No Person found with id: " + id));
    }

    public Person findPersonByUsername(String username) {
        return personDAO.findByUsername(username)
                .orElseThrow(() -> new PersonNotFoundException("No Person found with username: " + username));
    }

    public Person updatePerson(Person a) {
        return personDAO.save(a);
    }

    public boolean deletePersonById(int id) {
        personDAO.deleteById(id);

        if (!personDAO.existsById(id)) {
            // Successful message
            return true;
        } else {
            return false;
        }


    }
    public Person addPerson(Person t){
        Person returnedPerson = personDAO.save(t);

        if (returnedPerson.getId() > 0){
            return returnedPerson;
        } else{
            return null;
        }
    }

}

