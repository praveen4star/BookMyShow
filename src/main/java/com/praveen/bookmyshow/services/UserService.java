package com.praveen.bookmyshow.services;


import com.praveen.bookmyshow.models.User;
import com.praveen.bookmyshow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User signUpUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(!userOptional.isEmpty()){
            throw new RuntimeException("User already exists");
        }
        else{
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            return userRepository.save(user);
        }
    }
}
