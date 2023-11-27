package com.project.eat.eatbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.io.IOException;

// encapsulates the business logic related to user management (creating a user, finding a user by username and password, updating the user, deleting the user)
// uses the functions from UserRepository to do so, serves as another layer 
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
     private final BCryptPasswordEncoder passwordEncoder;
    

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public boolean authenticateUser(String username, String password) {
        // Retrieve the user by username from the database
        User user = userRepository.findUserByUsernameAndPassword(username, password);

        // Check if the user exists and the password matches
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return true;
        } else {
            return false; // Authentication failed
        }
    }

    // Find user by username and password 
    public User findUser(String username, String Password) throws IOException {
        User user = userRepository.findUserByUsernameAndPassword(username, Password); 
         if (user != null) {
            // User found, returns the correct user
            return user; 
        } else {
            // Handle the case where the user is not found
            throw new IOException("User with the provided username and password not found.");
        }
    }

    public boolean registerUser(String username, String password, String email)
    {
        if (userRepository.existsByEmail(email) || userRepository.existsByUsername(username))
        {
            // registration error with preexisting email/username 
            return false;
        }

        else 
        {
            String encodedPassword = passwordEncoder.encode(password);
            User newUser = new User(username,  encodedPassword, false, email); 
            try {
                userRepository.save(newUser);
            } catch (Exception e) {
                e.printStackTrace();
                // handle exception
            }
            return true; 
        }
    }
}
