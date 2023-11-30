package com.project.eat.eatbackend;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// The controller acts as the intermediary between the frontend and the backend, where the front end sends HTTP requests to specific 
// URLS. UserController controls user functionalities such as logging in, registering, 

@RestController
@RequestMapping("/")
@CrossOrigin // Allows requests from all origins
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody UserLoginDTO userLoginDto) throws IOException {
        boolean isAuthenticated = userService.authenticateUser(userLoginDto.getUsername(), userLoginDto.getPassword());
        Map<String, Object> response = new HashMap<>();
        if (isAuthenticated) {
            User user = userService.findUser(userLoginDto.getUsername(), userLoginDto.getPassword());
            response.put("success", true);
            //response.put("userId", user.getId());    // Assuming you have a getId() in your User class
            response.put("email", user.getEmail());  // Assuming you have getEmail() in your User class
            response.put("name", user.getUsername());  
            response.put("redirectUrl", "/dining");
        } else {
            response.put("success", false);
            response.put("message", "Invalid username or password.");
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterDTO userRegisterDto) {

        boolean isRegistered = userService.registerUser(userRegisterDto.getUsername(), userRegisterDto.getPassword(), userRegisterDto.getEmail());

        Map<String, Object> response = new HashMap<>();

        if (isRegistered) {
            response.put("success", true);
            response.put("redirectUrl", "/dining");
        } else {
            response.put("success", false);
            response.put("message", "Registration failed.");
        }
        return ResponseEntity.ok(response);

    }

    /*
     * @PostMapping("/login")
     * public String authenticateUser(@RequestParam("username") String username,
     * 
     * @RequestParam("password") String password) {
     * boolean isAuthenticated = userService.authenticateUser(username, password);
     * if (isAuthenticated == true) {
     * return "redirect:/dining";
     * }
     * 
     * else {
     * // error authenticating user, maybe reshow the login page in red, and attempt
     * // again which redirects back to login page
     * return "loginerror";
     * }
     * }
     * 
     * @PostMapping("/register")
     * public String registerUser(@RequestParam("username") String username,
     * 
     * @RequestParam("password") String password,
     * 
     * @RequestParam("email") String email) {
     * boolean isRegistered = userService.registerUser(username, password, email);
     * if (isRegistered) {
     * return "registersuccess";
     * }
     * 
     * else {
     * return "registererror";
     * }
     * }
     */

}
