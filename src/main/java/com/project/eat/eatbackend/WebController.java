package com.project.eat.eatbackend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/welcome")
    public String WelcomePage() {
        // this needs to return the html welcome page
        return "welcome"; 
    }

   @GetMapping("/login")
   public String Login()
    {
    // displays the existing user form 
    return "index"; 
    }

    @GetMapping("/loginerror")
    public String LoginError()
    {
        return "loginerror"; 
    }

   // @GetMapping("/register")
   // public String Register()
  //  {
        // displays the register form 
    //    return "register";
   // }
}