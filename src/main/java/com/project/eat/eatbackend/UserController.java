package com.project.eat.eatbackend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// The controller acts as the intermediary between the frontend and the backend, where the front end sends HTTP requests to specific 
// URLS. UserController controls user functionalities such as logging in, registering, 

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String authenticateUser(@RequestParam("username") String username, @RequestParam("password") String password)
    {
        boolean isAuthenticated = userService.authenticateUser(username, password); 
        if (isAuthenticated == true)
        {
            return "redirect:/dining"; 
        }

        else 
        {
            // error authenticating user, maybe reshow the login page in red, and attempt again which redirects back to login page
            return "loginerror"; 
        }
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email) 
    {
        boolean isRegistered = userService.registerUser(username, password, email); 
        if (isRegistered)
        {
            return "registersuccess";
        }

        else 
        {
            return "registererror"; 
        }
    }

}
