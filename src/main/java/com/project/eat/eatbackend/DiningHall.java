package com.project.eat.eatbackend;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Table(name = "DiningHall")
public class DiningHall {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long DiningHall_id;

    @Column(name = "DiningHall_name") 
    private String DiningHall_name; 

    public DiningHall() {

    }

    public DiningHall(String name)
    {
        this.DiningHall_name = name; 
    }

    public String getName()
    {
        return DiningHall_name; 
    }

    public long getID()
    {
        return DiningHall_id; 
    }
}
