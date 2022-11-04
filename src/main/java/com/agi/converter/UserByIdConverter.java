package com.agi.converter;

import com.agi.core.user.User;
import com.agi.repository.UserRepository;

public class UserByIdConverter {
    private final UserRepository userRepository;

    public UserByIdConverter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User convert(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public User convertByUsername(String username){
        return userRepository.findByUsername(username).orElse(null);
    }
}
