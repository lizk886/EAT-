package com.project.eat.eatbackend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

     @GetMapping("/")
   public String Login()
    {
    // displays the existing user form 
    return "index"; 
    }


   @GetMapping("/dining")
   public String Register()
    {
    // displays the dining hall 
        return "diningViewer";
   }
}