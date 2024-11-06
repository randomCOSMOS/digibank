package com.appproject.digibank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.appproject.digibank.model.User;

public interface UserRepo extends JpaRepository<User, Integer>{
    List<User> findByEmail(String email);
}
