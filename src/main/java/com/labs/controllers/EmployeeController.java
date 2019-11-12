package com.labs.controllers;

import java.util.List;
import java.util.Optional;

import com.labs.repositories.EmployeeRepository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping(value = "/", produces = "application/json")
    public List<com.labs.models.Employee> employeeList() {
        return employeeRepository.findAll();
    }

    @RequestMapping(value = "/{id}", produces = "application/json")
    public Optional<com.labs.models.Employee> employeeById(@PathVariable("id") ObjectId id) {
        return employeeRepository.findById(id);
    }
}