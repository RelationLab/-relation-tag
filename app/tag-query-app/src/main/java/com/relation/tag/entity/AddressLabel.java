package com.relation.tag.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Data
@Document(indexName="labels")
public class AddressLabel {
    @Id
    private String id;
    private String address;
    private List<Assets> assets;
    @Field(type= FieldType.Nested, store=true)
    private List<Labels> labels;
}
