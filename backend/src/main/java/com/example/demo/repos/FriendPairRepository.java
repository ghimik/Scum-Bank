package com.example.demo.repos;

import com.example.demo.models.Account;
import com.example.demo.models.FriendsPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendPairRepository extends JpaRepository<FriendsPair, Long> {

    List<FriendsPair> findByFirst(Account first);

    List<FriendsPair> findByFirstOrSecond(Account first, Account second);


    Optional<FriendsPair> findByFirstAndSecond(Account first, Account second);

}
