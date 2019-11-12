package com.labs.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Employee")
public class Employee {
    @Id
    private final ObjectId id;
    private final String firstName;
    private final String lastName;

    public Employee(ObjectId id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public ObjectId getId() {
        return id;
    }
}