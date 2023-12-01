package com.project.eat.eatbackend;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.scheduling.annotation.Async;


@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Async
    @Transactional
    public void incrementLikes(Long itemId) {
        MenuItem menuItem = menuItemRepository.findById(itemId)
            .orElseThrow(() -> new RuntimeException("MenuItem not found"));
        menuItem.setLikes(menuItem.getLikes() + 1);
        menuItemRepository.save(menuItem);
    }

    @Async
    @Transactional
    public void incrementDislikes(Long itemId) {
        MenuItem menuItem = menuItemRepository.findById(itemId)
            .orElseThrow(() -> new RuntimeException("MenuItem not found"));
        menuItem.setDislikes(menuItem.getDislikes() + 1);
        menuItemRepository.save(menuItem);
    }
}
