package com.appproject.digibank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appproject.digibank.model.User;
import com.appproject.digibank.repository.UserRepo;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public User saveUser(User user){
        return userRepo.save(user);
    }

    public List<User> getUserByEmail(String email){
        return userRepo.findByEmail(email);
    }
}
