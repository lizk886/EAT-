package com.project.eat.eatbackend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.ResponseEntity;




@RestController
@RequestMapping("/menuItems")
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @PostMapping("/{id}/like")
    public ResponseEntity<?> incrementLikes(@PathVariable Long id) {
        menuItemService.incrementLikes(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/dislike")
    public ResponseEntity<?> incrementDislikes(@PathVariable Long id) {
        menuItemService.incrementDislikes(id);
        return ResponseEntity.ok().build();
    }
}
