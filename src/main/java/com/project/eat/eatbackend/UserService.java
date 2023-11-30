package com.project.eat.eatbackend;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import jakarta.transaction.Transactional;

// encapsulates the business logic related to user management (creating a user, finding a user by username and password, updating the user, deleting the user)
// uses the functions from UserRepository to do so, serves as another layer 
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean authenticateUser(String username, String password) {
        // Retrieve the user by username from the database
         password=DigestUtils.md5DigestAsHex(password.getBytes());
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
        Password=DigestUtils.md5DigestAsHex(Password.getBytes());
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
            // using md5 for password encryption 
            password=DigestUtils.md5DigestAsHex(password.getBytes());
            User newUser = new User(username, password, false, email); 
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
