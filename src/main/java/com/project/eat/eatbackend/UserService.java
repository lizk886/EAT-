package com.project.eat.eatbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;

// encapsulates the business logic related to user management (creating a user, finding a user by username and password, updating the user, deleting the user)
// uses the functions from UserRepository to do so, serves as another layer 
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Creating a user 
    public User createUser(User newUser) {
        return userRepository.save(newUser);
    }

    public boolean authenticateUser(String username, String password) {
        // Retrieve the user by username from the database
        User user = userRepository.findUserByUsernameAndPassword(username, password);

        // Check if the user exists and the password matches
        if (user != null && user.getPassword().equals(password)) {
            return true; // Authentication successful
        } else {
            return false; // Authentication failed
        }
    }

    // Find user by username and password 
    public User findUser(String username, String Password) throws IOException {
        User user = userRepository.findUserByUsernameAndPassword(username, Password); 
         if (user != null) {
            // User found, returns the correct userINSERT INTO Users (id, id, Username, Email, Password, is_Guest)
            return user; 
        } else {
            // Handle the case where the user is not found
            throw new IOException("User with the provided username and password not found.");
        }
    }
}