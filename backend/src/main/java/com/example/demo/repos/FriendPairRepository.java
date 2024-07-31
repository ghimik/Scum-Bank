package com.example.demo.repos;

import com.example.demo.models.Account;
import com.example.demo.models.FriendsPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendPairRepository extends JpaRepository<FriendsPair, Long> {

    List<FriendsPair> findByFirst(Account first);

}
