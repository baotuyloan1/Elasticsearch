package com.baond.springdataelasticsearch.query_annotation;

import com.baond.springdataelasticsearch.AbstractTest;
import com.baond.springdataelasticsearch.entity.Article;
import com.baond.springdataelasticsearch.repository.ArticleRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class QueryAnnotationTest extends AbstractTest {

    @Autowired
    private ArticleRepository repository;

    @BeforeAll
    public void dataSetup() {
        var articles = this.readResource("config/articles/articles.json", new TypeReference<List<Article>>() {
        });
        this.repository.saveAll(articles);
        Assertions.assertEquals(11, this.repository.count());
    }

    @Test
    public void searchArticle(){
        var searchHits = this.repository.search("spring seasen");
        searchHits.forEach(this.print());
        Assertions.assertEquals(4, searchHits.getTotalHits());
    }


}
