package com.agi.service;

import com.agi.payload.response.UserResponse;

import java.util.List;

public interface UserService {
    public List<UserResponse> indexStudents();
    public List<UserResponse> indexInstructors();
}
