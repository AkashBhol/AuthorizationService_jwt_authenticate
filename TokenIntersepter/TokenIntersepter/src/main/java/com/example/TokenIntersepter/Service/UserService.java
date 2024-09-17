package com.example.TokenIntersepter.Service;


import com.example.TokenIntersepter.Domain.User;

import java.util.List;

public interface UserService {

    User save(User user);

    List<User> findAll();

    String findById(Long id);
}