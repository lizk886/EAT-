package com.project.eat.eatbackend;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

     @GetMapping("/")
     public String Login()
     {
          return "index"; 
     }

     @GetMapping("/dining")
     public String dining(Model model) {
          // Create three DiningHall instances
          DiningHall dininghall1 = new DiningHall("USC Village Dining Hall");
          DiningHall dininghall2 = new DiningHall("Parkside Restaurant & Grill");
          DiningHall dininghall3 = new DiningHall("Everybody's Kitchen");

          // Add them to an ArrayList
          ArrayList<DiningHall> dininghalls = new ArrayList<>();
          dininghalls.add(dininghall1);
          dininghalls.add(dininghall2);
          dininghalls.add(dininghall3);

          // Add the list of DiningHall instances to the model
          model.addAttribute("dininghalls", dininghalls);
          return "dininghallviewer";
     }

     @GetMapping("/dininghall/USC-Village-Dining-Hall")
     public String uscvillage()
     {
          return "village"; 
     } 

     @GetMapping("/dininghall/Parkside-Restaurant-&-Grill")
     public String parkside()
     {
          return "parkside"; 
     }

     @GetMapping("/dininghall/Everybody's-Kitchen")
     public String evk()
     {
          return "evk"; 
     }
}