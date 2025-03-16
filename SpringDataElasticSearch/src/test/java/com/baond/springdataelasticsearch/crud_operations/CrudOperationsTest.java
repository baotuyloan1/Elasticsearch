package com.baond.springdataelasticsearch.crud_operations;

import com.baond.springdataelasticsearch.AbstractTest;
import com.baond.springdataelasticsearch.entity.Employee;
import com.baond.springdataelasticsearch.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;

import java.util.List;
import java.util.stream.IntStream;

public class CrudOperationsTest extends AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(CrudOperationsTest.class);

    @Autowired
    private EmployeeRepository repository;

    @Test
    public void crud() {
        var employee = this.createEmployee(1, "BaoND", 25);

        this.repository.save(employee);
        this.printAll();

        // find by id
        employee = this.repository.findById(1).orElseThrow();

        Assertions.assertEquals(25, employee.getAge());
        Assertions.assertEquals("BaoND", employee.getName());
        Assertions.assertEquals(1, employee.getId());

        employee.setAge(10);
        employee = this.repository.save(employee);
        this.printAll();
        Assertions.assertEquals(10, employee.getAge());

        this.repository.deleteById(1);
        Assertions.assertTrue(this.repository.findById(1).isEmpty());
        this.printAll();
    }

    @Test
    public void bulkCrud() {

        /*create*/
        var list = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> this.createEmployee(i, "name-" + i, 30 + i))
                .toList();

        this.repository.saveAll(list);
        this.printAll();

        Assertions.assertEquals(10, this.repository.count());

        /*read*/
        var ids = List.of(2, 4, 6);
        var iterable = this.repository.findAllById(ids);
        list = Streamable.of(iterable).toList();
        Assertions.assertEquals(3, list.size());

        /*update*/
        list.forEach(e -> e.setAge(e.getAge() + 10));
        this.repository.saveAll(list);
        this.printAll();

        this.repository.findAllById(ids)
                .forEach(e -> Assertions.assertEquals(e.getId() + 40, e.getAge()));

        /*delete*/
        this.repository.deleteAllById(ids);
        this.printAll();
        Assertions.assertEquals(7, this.repository.count());
    }

    private Employee createEmployee(int id, String name, int age) {
        var employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setAge(age);
        return employee;
    }

    private void printAll() {
        this.repository.findAll().forEach(e -> log.info("employee: {}", e));
    }
}
