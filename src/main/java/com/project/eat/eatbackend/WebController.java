package com.project.eat.eatbackend;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.transaction.Transactional;

@Controller
public class WebController {

     @Autowired
     private DiningHallRepository DiningHallRepository;

     @GetMapping("/")
     public String Login() {
          return "indexPage";
     }

     @Transactional
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
          // save it into the database
          DiningHallRepository.save(dininghall1);
          DiningHallRepository.save(dininghall2);
          DiningHallRepository.save(dininghall3);

          scrapeandclasscreation(dininghalls);

          // Add the list of DiningHall instances to the model
          model.addAttribute("dininghalls", dininghalls);
          return "dininghallviewer";
     }

     @Transactional
     private void scrapeandclasscreation(ArrayList<DiningHall> dininghalls) {
          try {
               String url = "https://hospitality.usc.edu/residential-dining-menus/?menu_date=November+30%2C+2023";
               Document doc = Jsoup.connect(url).get();

               // selecting every category (ex. Expo/Flexitarian etc and saving into a private
               // string under menuItem)
               // the column for each dining hall is divided by this div class
               Elements diningHallSections = doc.select("div.col-sm-6.col-md-4");
               for (Element section : diningHallSections) {
                    // extracting the dining hall name
                    String dhname = section.select("h3.menu-venue-title").text();

                    // Find the matching DiningHall object
                    DiningHall matchedDiningHall = null;
                    for (DiningHall hall : dininghalls) {
                         if (hall.getName().equalsIgnoreCase(dhname)) {
                              // makes the matchdininghall object to the matched dining hall object from the
                              // arrayList of dining halls
                              matchedDiningHall = hall;
                              break;
                         }
                    }

                    // (27 lines) "How do I skip the first <li> element with the text "Made to order
                    // omelet" and
                    // prefix every subsequent ingredient with "Made to order omelets ingredient"
                    // and save into my class MenuItem and DiningHall"
                    // ChatGPT, Apr 2023 version, OpenAI, 24 Nov. 2023 chat.openai.com/chat
                    // ...
                    if (matchedDiningHall != null) {
                         Elements categories = section.select("h4");
                         for (Element categoryElement : categories) {
                              String categoryName = categoryElement.text();
                              Element menuList = categoryElement.nextElementSibling();

                              if (menuList != null) {
                                   Elements menuItems = menuList.select("li");
                                   String firstMenuItemText = menuItems.first().text();
                                   boolean isMadeToOrderOmelets = firstMenuItemText
                                             .equalsIgnoreCase("MADE TO ORDER OMELETES");
                                   boolean isPhoBowlBar = firstMenuItemText.equalsIgnoreCase("PHO BOWL BAR");
                                   boolean isQueso = firstMenuItemText
                                             .equalsIgnoreCase("MADE TO ORDER BREAKFAST QUESADILLA BAR");

                                   for (Element menuItemElement : menuItems) {
                                        // Deleting the <span> elements from itemName
                                        menuItemElement.select("span").remove();
                                        String itemName = menuItemElement.text();

                                        boolean itemExists = false;
                                        for (MenuItem existingItem : matchedDiningHall.getMenu()) {
                                             // Check for duplicates based on itemName and categoryName
                                             if (existingItem.getItem_name().equals(itemName)
                                                       && existingItem.getCategory().equals(categoryName)) {
                                                  itemExists = true;
                                                  break;
                                             }
                                        }

                                        if (!itemExists) {
                                             String processedItemName = itemName; // Create a new variable
                                             if (isMadeToOrderOmelets) {
                                                  // Process all items with the prefix
                                                  processedItemName = "Made to Order Omelete ingredient: " + itemName;
                                             } else if (isPhoBowlBar) {
                                                  processedItemName = "Pho Bowl Bar ingredient: " + itemName;
                                             } else if (isQueso) {
                                                  processedItemName = "Made to Order Breakfast Quesadilla Bar ingredient: "
                                                            + itemName;
                                             }

                                             MenuItem menuItem = new MenuItem();
                                             menuItem.setDiningHall(matchedDiningHall);
                                             menuItem.setItem_name(processedItemName);
                                             menuItem.setCategory(categoryName);
                                             matchedDiningHall.getMenu().add(menuItem);
                                        }
                                   }
                              }
                         }
                    }

               }
          } catch (Exception e) {
               e.printStackTrace();
          }
     }

     @GetMapping("/dininghall/USC-Village-Dining-Hall")
     public String uscvillage(Model model) {
          List<DiningHall> villageDiningHalls = DiningHallRepository.findByName("USC Village Dining Hall");

          if (!villageDiningHalls.isEmpty()) {
               // Assuming you want to display the menu of the first dining hall in the list
               DiningHall firstVillageDiningHall = villageDiningHalls.get(0);
               model.addAttribute("menuItems", firstVillageDiningHall.getMenu());
          }
          return "village";
     }

     @GetMapping("/dininghall/Parkside-Restaurant-&-Grill")
     public String parkside(Model model) {
          List<DiningHall> parksideDiningHalls = DiningHallRepository.findByName("Parkside Restaurant & Grill");

          if (!parksideDiningHalls.isEmpty()) {
               DiningHall firstParksideDiningHall = parksideDiningHalls.get(0);
               model.addAttribute("menuItems", firstParksideDiningHall.getMenu());
          }
          return "parkside";
     }

     @GetMapping("/dininghall/Everybody's-Kitchen")
     public String evk(Model model) {
          List<DiningHall> evkDiningHalls = DiningHallRepository.findByName("Everybody's Kitchen");

          if (!evkDiningHalls.isEmpty()) {
               DiningHall firstevkDiningHall = evkDiningHalls.get(0);
               model.addAttribute("menuItems", firstevkDiningHall.getMenu());
          }
          return "evk";
     }

     @GetMapping("/indexPage")
     public String index() {
         return "indexPage"; 
     }
      @GetMapping("/UserProfile")
     public String profile() {
         return "UserProfile"; 
     }
}