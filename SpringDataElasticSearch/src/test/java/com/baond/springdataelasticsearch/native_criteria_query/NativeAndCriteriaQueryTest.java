package com.baond.springdataelasticsearch.native_criteria_query;

import com.baond.springdataelasticsearch.AbstractTest;
import com.baond.springdataelasticsearch.entity.Garment;
import com.baond.springdataelasticsearch.repository.GarmentRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.geo.Point;

import java.util.List;

public class NativeAndCriteriaQueryTest extends AbstractTest {

    @Autowired
    private GarmentRepository repository;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @BeforeAll
    public void dataSetup() {
        var garments = this.readResource("config/garments/garments.json", new TypeReference<List<Garment>>() {
        });
        this.repository.saveAll(garments);
        Assertions.assertEquals(20, this.repository.count());
    }

    @Test
    public void criteriaQuery() {
        /*it's a text field => can use token shirt. if name is a keyword, we have to use all the sentence */
        var nameIsShirt = Criteria.where("name").is("shirt");
        this.verify(nameIsShirt,1);

        var priceAbove100 = Criteria.where("price").greaterThan(100);
        this.verify(priceAbove100,5);

        this.verify(nameIsShirt.or(priceAbove100),6);

        var brandIsZara = Criteria.where("brand").is("Zara").boost(3.0f);
        this.verify(priceAbove100.and(brandIsZara.not()), 3);

        var fuzzyMatchShort = Criteria.where("name").fuzzy("short");
        this.verify(fuzzyMatchShort,1);

        // we can also do geo point
//        Criteria.where("location").within(point, distance);
    }


    private void verify(Criteria criteria, int expectedResultsCount){
         var query = CriteriaQuery.builder(criteria).build();
         var searchHits = this.elasticsearchOperations.search(query, Garment.class);
         searchHits.forEach(this.print());
         Assertions.assertEquals(expectedResultsCount, searchHits.getTotalHits());
    }
}
