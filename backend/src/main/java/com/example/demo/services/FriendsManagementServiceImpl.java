package com.example.demo.services;

import com.example.demo.models.projections.AccountProjection;
import com.example.demo.repos.AccountRepository;
import com.example.demo.repos.FriendPairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendsManagementServiceImpl implements FriendsManagementService {

    @Autowired
    private FriendPairRepository friendPairRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<AccountProjection> getAllFriends(Long id) {
        return friendPairRepository
                .findByFirst(accountRepository.findById(id).orElseThrow())
                .stream()
                .map(friendsPair -> {
                    return accountRepository.findById(friendsPair.getSecond().getId());
                })
                .map(account -> {
                    return new AccountProjection(account.orElseThrow().getUsername());
                })
                .toList();
    }
}
