package com.relation.tag.opensearch.repository;

import com.relation.tag.entity.AddressLabel;
import com.relation.tag.entity.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressLabelRepository extends CrudRepository<AddressLabel, String> {
    AddressLabel findByAddress(String address);
}
