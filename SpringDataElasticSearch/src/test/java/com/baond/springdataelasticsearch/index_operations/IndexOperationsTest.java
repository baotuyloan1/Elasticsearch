package com.baond.springdataelasticsearch.index_operations;

import com.baond.springdataelasticsearch.AbstractTest;
import com.baond.springdataelasticsearch.entity.Customer;
import com.baond.springdataelasticsearch.entity.Movie;
import com.baond.springdataelasticsearch.entity.Review;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;

public class IndexOperationsTest extends AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(IndexOperationsTest.class);

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    /*option 1*/
    @Test
    public void createIndex() {
        var indexOperations = this.elasticsearchOperations.indexOps(IndexCoordinates.of("albums"));
        Assertions.assertTrue(indexOperations.create());
        this.verify(indexOperations, 1, 1);

    }

    /*option 2*/
    @Test
    public void createIndexWithSettings(){
        var indexOperations = this.elasticsearchOperations.indexOps(Review.class);
        Assertions.assertTrue(indexOperations.create());
        this.verify(indexOperations, 2, 2);

    }

    /*option 3*/
    @Test
    public void createIndexWithSettingsAndMapping(){
        var indexOperations = this.elasticsearchOperations.indexOps(Customer.class);
        Assertions.assertTrue(indexOperations.createWithMapping());
        this.verify(indexOperations, 3,0);
    }

    /*option 4*/
    @Test
    public void createIndexWithFieldMapping(){
        var indexOperations = this.elasticsearchOperations.indexOps(Movie.class);
        Assertions.assertTrue(indexOperations.createWithMapping());
        this.verify(indexOperations, 1,1);
    }

    private void verify(IndexOperations indexOperations, int expectedShards, int expectedReplicas) {
        var settings = indexOperations.getSettings();

        log.info("setting: {}", settings);
        log.info("mapping: {}", indexOperations.getMapping());

        Assertions.assertEquals(String.valueOf(expectedShards), settings.get("index.number_of_shards"));
        Assertions.assertEquals(String.valueOf(expectedReplicas), settings.get("index.number_of_replicas"));

        /*delete index*/
        Assertions.assertTrue(indexOperations.delete());
    }
}
