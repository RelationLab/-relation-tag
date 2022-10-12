package com.relation.tag.opensearch.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface PersonRepository extends CrudRepository<Person, Long> {
    List<Person> findByLastname(String lastname);

    List<Person> findByFirstnameLike(String firstname);
}
