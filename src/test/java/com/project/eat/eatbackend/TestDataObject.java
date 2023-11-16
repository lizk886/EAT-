package com.project.eat.eatbackend;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TestDataObject {
    @Id
    private long id; 
    @Column
    private String username; 
}
