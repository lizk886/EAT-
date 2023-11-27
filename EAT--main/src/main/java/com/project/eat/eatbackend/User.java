package com.project.eat.eatbackend;
import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class User {
    // an Id is needed for a java entity class, in the context of using Java Persistance API
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "Email")
    private String email; 

    @Column(name = "Username") 
    private String username; 

    @Column(name = "Password") 
    private String password; 

    @Column(name = "is_Guest")
    private boolean isGuest; 

    // constructor INSERT INTO Users (id, Username, Email, Password, isGuest)
    
    public User() {
    }
   
    public User(String username, String password, boolean isGuest, String email)
    {
        this.username = username; 
        this.password = password; 
        this.isGuest = isGuest; 
        this.email = email; 
    }
    
    public String getUsername()
    {
        return username; 
    }

    public String getPassword()
    {
        return password; 
    }

    public boolean getisGuest()
    {
        return isGuest; 
    }

    public void setUsername(String n)
    {
        this.username = n; 
    }

    public void setPassword(String p)
    {
        this.password = p; 
    }

    public void setisGuest(boolean val)
    {
        this.isGuest = val; 
    }
}
