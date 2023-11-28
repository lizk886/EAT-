package com.project.eat.eatbackend;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DiningHallController {

    @GetMapping("/diningHallViewer")
    public String showDiningHalls(Model model) {

        List<DiningHall> dininghalls = new ArrayList<>();
        dininghalls.add(new DiningHall("Everybody's Kitchen"));
        dininghalls.add(new DiningHall("Parkside Dining Hall"));
        dininghalls.add(new DiningHall("Village Dining Hall"));

        model.addAttribute("dininghalls", dininghalls);

        System.out.println("hello");

        return "diningHallViewer"; // Name of the Thymeleaf template (e.g., dininghalls.html)
    }
}