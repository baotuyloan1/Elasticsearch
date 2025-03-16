package com.baond.springdataelasticsearch;

import org.springframework.boot.SpringApplication;

public class TestSpringDataElasticSearchApplication {

    public static void main(String[] args) {
        SpringApplication.from(SpringDataElasticSearchApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
