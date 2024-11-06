package com.appproject.digibank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "TRANSACTION")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String email;   
    private Integer amount;
    private String type;
    private String date;

    private String block;

    @Override
    public String toString() {
        return "Transaction [id=" + id + ", email=" + email + ", amount=" + amount + ", type=" + type + ", block="
                + block + "]";
    } 

}
