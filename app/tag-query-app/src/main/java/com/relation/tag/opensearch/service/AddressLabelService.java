package com.relation.tag.opensearch.service;

import com.relation.tag.entity.AddressLabel;
import com.relation.tag.opensearch.repository.AddressLabelRepository;
import org.apache.lucene.search.join.ScoreMode;
import org.opensearch.data.client.orhlc.NativeSearchQueryBuilder;
import org.opensearch.index.query.BoolQueryBuilder;
import org.opensearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.opensearch.index.query.QueryBuilders.nestedQuery;
import static org.opensearch.index.query.QueryBuilders.termQuery;

@Service
public class AddressLabelService {
    @Autowired
    private AddressLabelRepository repository;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;


    public AddressLabel findByAddress(String address) {
        return repository.findByAddress(address);
    }

    public List<AddressLabel> findByLabels(List<String> labels) {
        Query searchQuery = getNestedQuery(labels);
        SearchHits<AddressLabel> addressLabels = elasticsearchOperations.search(searchQuery, AddressLabel.class);
        return null;
    }

    private Query getNestedQuery(List<String> labels) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        labels.forEach(item -> {
            queryBuilder.should(nestedQuery(
                    "labels", //
                    termQuery("labels.name", item), //
                    ScoreMode.None));
        });
        return new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();
    }


}
