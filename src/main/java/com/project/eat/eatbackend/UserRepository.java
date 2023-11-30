package com.project.eat.eatbackend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// Repositories are used as the data access layer, inherits JpaRepository to inherit CRUD methods
// CRUD meaning Create, Read, Update, Delete 
// You don't need to explicitly declare these methods (and others)
// it self puts the data into the database 

/* save(S entity): Save an entity and flush changes instantly.
findById(ID id): Retrieve an entity by its ID.
findAll(): Get all entities.
deleteById(ID id): Delete the entity with the given ID.
delete(T entity): Delete a given entity.
count(): Returns the number of entities.*/ 

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    @Query("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
    User findUserByUsernameAndPassword(
        @Param("username") String username, 
        @Param("password") String password
    );
    

    // query method derivation by Spring Boot
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
