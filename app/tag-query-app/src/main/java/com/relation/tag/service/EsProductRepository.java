package com.relation.tag.service;

import com.relation.tag.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface EsProductRepository extends ElasticsearchRepository<Product,String>
{
    List<Product> findByskuNoAndTilte(String sku, String title);
}
