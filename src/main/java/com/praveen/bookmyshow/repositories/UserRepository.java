package com.praveen.bookmyshow.repositories;

import com.praveen.bookmyshow.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long userId);
    User save(User user);
    Optional<User> findByEmail(String email);
}
