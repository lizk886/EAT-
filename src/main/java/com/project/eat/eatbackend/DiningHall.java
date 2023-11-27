package com.project.eat.eatbackend;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "DiningHall")
public class DiningHall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DiningHall_id")
    private long DiningHall_id;

    @Column(name = "DiningHall_name") 
    private String name; 

    @OneToMany(mappedBy = "diningHall", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuItem> menu = new ArrayList<>();

    public DiningHall() {
    }

    public DiningHall(String name)
    {
        this.name = name; 
    }

    public String getName()
    {
        return name; 
    }

    public long getDiningHall_id()
    {
        return DiningHall_id; 
    }

    public List<MenuItem> getMenu()
    {
        return menu; 
    }

    public void addMenuItem(MenuItem item) {
        this.menu.add(item);
    }
}
