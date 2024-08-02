package com.example.demo.services;

import com.example.demo.models.Account;
import com.example.demo.models.FriendsPair;
import com.example.demo.models.projections.AccountProjection;
import com.example.demo.repos.AccountRepository;
import com.example.demo.repos.FriendPairRepository;
import jakarta.transaction.Transactional;
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
        var myacc = accountRepository.findById(id).orElseThrow();
        return friendPairRepository
                .findByFirstOrSecond(myacc, myacc)
                .stream()
                .map(friendsPair -> {
                    var first = accountRepository.findById(friendsPair.getFirst().getId()).orElseThrow();
                    var second = accountRepository.findById(friendsPair.getSecond().getId()).orElseThrow();
                    if (first.getId().equals(myacc.getId()))
                        return second;
                    else
                        return first;
                })
                .map(account -> {
                    return new AccountProjection(account.getUsername(), account.getAvatar());
                })
                .toList();
    }

    @Override
    @Transactional
    public void befriend(Long initiatorId, Long receiverId) {
        saveFriendPair(accountRepository.findById(initiatorId).orElseThrow(),
                accountRepository.findById(receiverId).orElseThrow());
    }

    @Override
    @Transactional
    public void befriend(Long initiatorId, String receiverName) {
        saveFriendPair(accountRepository.findById(initiatorId).orElseThrow(),
                accountRepository.findByUsername(receiverName));
    }

    @Override
    public Boolean areFriends(Long first, Long second) {
        return  friendPairRepository.findByFirstAndSecond(
                    accountRepository.findById(first).orElseThrow(),
                    accountRepository.findById(second).orElseThrow())
                .isPresent();
    }

    @Override
    public Boolean areFriends(Account first, Account second) {
        return friendPairRepository
                .findByFirstAndSecond(first, second)
                .isPresent();
    }

    private void saveFriendPair(Account first, Account second) {
        FriendsPair friendsPair = new FriendsPair();
        friendsPair.setFirst(first);
        friendsPair.setSecond(second);

        friendPairRepository.saveAndFlush(friendsPair);
    }
}
