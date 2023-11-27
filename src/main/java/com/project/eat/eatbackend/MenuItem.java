package com.project.eat.eatbackend;

import jakarta.persistence.*;

@Entity
@Table(name = "MenuItem")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Item_id")
    private long Item_id; 

    @Column(name = "Item_name")
    private String Item_name; 

    @Column(name = "Category")
    private String Category; 

    @Column(name = "Likes")
    private int likes;

    @Column(name = "Dislikes")
    private int dislikes; 

    // Many-to-one relationship with DiningHall
    @ManyToOne
    @JoinColumn(name = "DiningHall_id")
    private DiningHall diningHall;

    // Getters and setters

    public long getItem_id() {
        return Item_id;
    }

    public void setItem_id(long item_id) {
        this.Item_id = item_id;
    }

    public String getItem_name() {
        return Item_name;
    }

    public void setItem_name(String item_name) {
        this.Item_name = item_name;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        this.Category = category;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public DiningHall getDiningHall() {
        return diningHall;
    }

    public void setDiningHall(DiningHall diningHall) {
        this.diningHall = diningHall;
    }
}

