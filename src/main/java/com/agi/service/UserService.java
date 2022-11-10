package com.agi.service;

import com.agi.payload.response.MessageResponse;
import com.agi.payload.response.ProfileResponse;
import com.agi.payload.response.UserResponse;

import java.util.List;

public interface UserService {
    public List<UserResponse> indexStudents();
    public List<UserResponse> indexInstructors();
    public ProfileResponse show(Long id);
    public MessageResponse delete(Long id);
}
