package com.agi.service.impl;

import com.agi.core.user.Role;
import com.agi.core.user.User;
import com.agi.payload.response.UserResponse;
import com.agi.repository.RoleRepository;
import com.agi.repository.UserRepository;
import com.agi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public List<UserResponse> indexStudents() {
        List<User> users = userRepository.findUsersByRolesId(3L);
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user: users) {
            UserResponse userResponse = new UserResponse();
            userToUserResponse(user, userResponse);
            userResponses.add(userResponse);
        }
        return userResponses;
    }

    @Override
    public List<UserResponse> indexInstructors() {
        List<User> users = userRepository.findUsersByRolesId(2L);
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user: users) {
            UserResponse userResponse = new UserResponse();
            userToUserResponse(user, userResponse);
            userResponses.add(userResponse);
        }
        return userResponses;
    }

    public void userToUserResponse(User user, UserResponse userResponse){
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());
    }
}
