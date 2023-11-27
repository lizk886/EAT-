package com.project.eat.eatbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Service
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public MenuItemService(MenuItemRepository menuItemRepository, ReviewRepository reviewRepository) {
        this.menuItemRepository = menuItemRepository;
        this.reviewRepository = reviewRepository;
    }

    public void addReviewToMenuItem(Long menuItemId, Review review) {
        MenuItem menuItem = menuItemRepository.findById(menuItemId)
            .orElseThrow(() -> new NoSuchElementException("MenuItem not found with id: " + menuItemId));
        menuItem.addReview(review);
        review.setMenuItem(menuItem);
        reviewRepository.save(review);
    }

    public void likeMenuItem(Long menuItemId) {
        MenuItem menuItem = menuItemRepository.findById(menuItemId)
            .orElseThrow(() -> new NoSuchElementException("MenuItem not found with id: " + menuItemId));
        menuItem.like();
        menuItemRepository.save(menuItem);
    }

    public void dislikeMenuItem(Long menuItemId) {
        MenuItem menuItem = menuItemRepository.findById(menuItemId)
            .orElseThrow(() -> new NoSuchElementException("MenuItem not found with id: " + menuItemId));
        menuItem.dislike();
        menuItemRepository.save(menuItem);
    }
}


