package com.project.eat.eatbackend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// the save() method is premade here

@Repository
public interface DiningHallRepository extends JpaRepository<DiningHall, Long> {

}
