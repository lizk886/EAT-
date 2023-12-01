package com.project.eat.eatbackend;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// the save() method is premade here

@Repository
public interface DiningHallRepository extends JpaRepository<DiningHall, Long> {
    List<DiningHall> findByName(String name);
}
