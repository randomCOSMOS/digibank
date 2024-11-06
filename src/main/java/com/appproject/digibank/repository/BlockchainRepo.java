package com.appproject.digibank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.appproject.digibank.model.Transaction;

public interface BlockchainRepo extends JpaRepository<Transaction, Integer>{
    List<Transaction> findByEmail(String email);
}
