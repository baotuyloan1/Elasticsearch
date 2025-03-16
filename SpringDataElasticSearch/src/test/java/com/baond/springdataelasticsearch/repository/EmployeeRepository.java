package com.baond.springdataelasticsearch.repository;

import com.baond.springdataelasticsearch.entity.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EmployeeRepository extends ElasticsearchRepository<Employee, Integer> {
}
