package com.project.eat.eatbackend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.eat.eatbackend.MenuItemService;
import com.project.eat.eatbackend.Review;
import com.project.eat.eatbackend.MenuItem;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/menuItems")
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @PostMapping("/{menuItemId}/review")
    public ResponseEntity<?> addReview(@PathVariable Long menuItemId, @RequestBody Review review) {
        menuItemService.addReviewToMenuItem(menuItemId, review);
        return ResponseEntity.ok("Review added successfully");
    }

    @PostMapping("/{menuItemId}/like")
    public ResponseEntity<?> likeMenuItem(@PathVariable Long menuItemId) {
        menuItemService.likeMenuItem(menuItemId);
        return ResponseEntity.ok("Liked successfully");
    }

    @PostMapping("/{menuItemId}/dislike")
    public ResponseEntity<?> dislikeMenuItem(@PathVariable Long menuItemId) {
        menuItemService.dislikeMenuItem(menuItemId);
        return ResponseEntity.ok("Disliked successfully");
    }
}
