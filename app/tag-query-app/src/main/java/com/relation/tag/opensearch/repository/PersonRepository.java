package com.relation.tag.opensearch.repository;

import com.relation.tag.entity.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface PersonRepository extends CrudRepository<Person, Long> {
    List<Person> findByLastname(String lastname);

    List<Person> findByFirstnameLike(String firstname);
}
