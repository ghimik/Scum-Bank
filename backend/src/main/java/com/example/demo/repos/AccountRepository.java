package com.example.demo.repos;

import com.example.demo.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    public final Integer NUMERIC_SCALE_FACTOR = 2;

    Account findByUsernameAndPassword(String username, String password);

    Account findByUsername(String username);

}

