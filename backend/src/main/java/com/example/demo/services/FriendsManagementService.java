package com.example.demo.services;

import com.example.demo.models.projections.AccountProjection;

import java.util.List;

public interface FriendsManagementService {

    List<AccountProjection> getAllFriends(Long id);

}
