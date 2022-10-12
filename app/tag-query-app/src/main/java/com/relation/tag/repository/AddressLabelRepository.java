package com.relation.tag.repository;

import com.relation.tag.entity.AddressLabel;
import org.springframework.data.repository.CrudRepository;

public interface AddressLabelRepository extends CrudRepository<AddressLabel, String> {
    AddressLabel findByAddress(String address);
}
