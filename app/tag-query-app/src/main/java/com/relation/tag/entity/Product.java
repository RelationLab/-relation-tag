package com.relation.tag.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Document(indexName = "product", createIndex = true)
public class Product implements Serializable
{
    private static final long serialVersionUID = -2408117939493050954L;

    @Id
    @Field(type = FieldType.Text)
    private String id;

    @Field(type = FieldType.Text)
    private String skuNo;

    @Field(type = FieldType.Text)
    private String tilte;

    @Field(type = FieldType.Double)
    private BigDecimal price;

    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    private Date createDate;
}
