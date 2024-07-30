package com.example.demo.repos;

import com.example.demo.models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    @Query(value = "SELECT bacc.id, bacc.balance FROM BankAccount bacc " +
            "INNER JOIN account acc ON (acc.id = bacc.id) " +
            "WHERE acc.id = ?1", nativeQuery = true)
    BankAccount findByMainAccountId(Long id);

    @Query(value = "UPDATE bankaccount as ba " +
                   "SET balance=(balance + ?2)" +
                   "WHERE ba.id = ?1",nativeQuery = true)
    @Modifying
    void addMoney(Long id, BigDecimal amount);

    @Query(value = "UPDATE bankaccount as ba " +
                   "SET balance=(balance - ?2)" +
                   "WHERE ba.id = ?1",nativeQuery = true)
    @Modifying
    void annigilateMoney(Long id, BigDecimal amount);
}
