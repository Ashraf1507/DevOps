package com.agi.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class ProfileResponse {
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
    private List<CourseResponse> courses;
}