package com.relation.tag.opensearch.service;

import com.relation.tag.entity.Person;
import com.relation.tag.opensearch.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyService {
    @Autowired
    private PersonRepository repository;

    public void doWork() {
        System.out.println("operations===" + 1111);
        repository.deleteAll();

        Person person = new Person();
        person.setFirstname("Oliver");
        person.setLastname("Gierke");
        repository.save(person);

        List<Person> lastNameResults = repository.findByLastname("Gierke");
        List<Person> firstNameResults = repository.findByFirstnameLike("Oli");
        System.out.println("lastNameResults===" + lastNameResults);

    }
}
