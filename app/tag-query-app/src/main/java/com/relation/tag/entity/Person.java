package com.relation.tag.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName="index_person")
public class Person {
    @Id
    private String id;
    private String firstname;
    private String lastname;
}
