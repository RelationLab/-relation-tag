package com.relation.tag.opensearch;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class MyService {
    private final PersonRepository repository;

    public MyService(PersonRepository repository) {
        this.repository = repository;
    }

    public void doWork() {

        repository.deleteAll();

        Person person = new Person();
        person.setFirstname("Oliver");
        person.setLastname("Gierke");
        repository.save(person);

        List<Person> lastNameResults = repository.findByLastname("Gierke");
        List<Person> firstNameResults = repository.findByFirstnameLike("Oli");
    }
}
