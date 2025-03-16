package com.baond.springdataelasticsearch.query_method;

import com.baond.springdataelasticsearch.AbstractTest;
import com.baond.springdataelasticsearch.entity.Product;
import com.baond.springdataelasticsearch.repository.ProductRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Streamable;

import java.util.List;

public class QueryMethodTest extends AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(QueryMethodTest.class);

    @Autowired
    private ProductRepository repository;

    @BeforeAll
    public void dataSetup() {
        var products = this.readResource("config/products/products.json", new TypeReference<List<Product>>() {
        });
        this.repository.saveAll(products);
        Assertions.assertEquals(20, this.repository.count());

    }

    @Test
    public void findByCategory() {
        var searchHits = this.repository.findByCategory("Furniture");
        searchHits.forEach(this.print());
        Assertions.assertEquals(4, searchHits.getTotalHits());
    }

    @Test
    public void findByCategories() {
        var searchhits = this.repository.findByCategoryIn(List.of("Furniture", "Beauty"));
        searchhits.forEach(this.print());
        Assertions.assertEquals(8, searchhits.getTotalHits());
    }

    @Test
    public void findByCategoryAndBrand() {
        var searchHits = this.repository.findByCategoryAndBrand("Furniture", "Ikea");
        searchHits.forEach(this.print());
        Assertions.assertEquals(2, searchHits.getTotalHits());
    }


    @Test
    public void findByName() {
        /*by default, ES will use OR operator between the words(split words) */
        /*however, the Spring Data team decided to use AND operator*/
        /*match query, AND operator*/
        var searchHits = this.repository.findByName("coffee table");
        searchHits.forEach(this.print());
        Assertions.assertEquals(1, searchHits.getTotalHits());
    }

    @Test
    public void findByPriceLessThan() {
        var searchHits = this.repository.findByPriceLessThan(80);
        searchHits.forEach(this.print());
        Assertions.assertEquals(5, searchHits.getTotalHits());
    }

    @Test
    public void findByPriceBetween() {
        var searchHits = this.repository.findByPriceBetween(10, 120, Sort.by("price").descending());
        searchHits.forEach(this.print());
        Assertions.assertEquals(8, searchHits.getTotalHits());
    }

    @Test
    public void findAllSortByQuantity() {
        var iterable = this.repository.findAll(Sort.by("quantity"));
        iterable.forEach(this.print());
        Assertions.assertEquals(20, Streamable.of(iterable).toList().size());
    }

    @Test
    public void findByCategoryWithPagination() {
        var searchPage = this.repository.findByCategory("Electronics", PageRequest.of(0,4));
        searchPage.getSearchHits().forEach(this.print());
        Assertions.assertEquals(0,searchPage.getNumber());
        Assertions.assertEquals(3,searchPage.getTotalPages());
        Assertions.assertEquals(12,searchPage.getTotalElements());


    }
}
