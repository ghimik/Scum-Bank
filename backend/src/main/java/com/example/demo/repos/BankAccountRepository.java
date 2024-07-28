package com.example.demo.repos;

import com.example.demo.models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    @Query(value = "SELECT bacc.id, bacc.balance FROM BankAccount bacc " +
            "INNER JOIN account acc ON (acc.id = bacc.id) " +
            "WHERE acc.id = ?1", nativeQuery = true)
    BankAccount findByMainAccountId(Long id);
}
