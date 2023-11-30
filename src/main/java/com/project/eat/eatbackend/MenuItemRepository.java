package com.project.eat.eatbackend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.eat.eatbackend.MenuItem;


// the save() method is premade here

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    // This might already exist with standard CRUD operations
}

