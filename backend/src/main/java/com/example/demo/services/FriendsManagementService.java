package com.example.demo.services;

import com.example.demo.models.Account;
import com.example.demo.models.projections.AccountProjection;

import java.util.List;

public interface FriendsManagementService {

    List<AccountProjection> getAllFriends(Long id);

    void befriend(Long initiatorId, Long recieverId);

    void befriend(Long initiatorId, String receiverName);

    Boolean areFriends(Long first, Long second);

    Boolean areFriends(Account first, Account second);

}
