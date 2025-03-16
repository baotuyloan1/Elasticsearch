package com.baond.springdataelasticsearch.repository;


import com.baond.springdataelasticsearch.entity.Garment;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface GarmentRepository extends ElasticsearchRepository<Garment, String> {
}
