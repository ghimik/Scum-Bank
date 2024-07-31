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

    private void saveFriendPair(Account first, Account second) {
        FriendsPair friendsPair = new FriendsPair();
        friendsPair.setFirst(first);
        friendsPair.setSecond(second);

        friendPairRepository.saveAndFlush(friendsPair);
    }
}
