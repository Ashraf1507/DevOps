package com.agi.controller;

import com.agi.payload.response.UserResponse;
import com.agi.repository.UserRepository;
import com.agi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/students")
    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    public ResponseEntity<List<UserResponse>> indexStudents(){
        List<UserResponse> userResponses = userService.indexStudents();
        return new ResponseEntity<>(userResponses, HttpStatus.OK);
    }
    @GetMapping("/instructors")
    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    public ResponseEntity<List<UserResponse>> indexInstructors(){
        List<UserResponse> userResponses = userService.indexInstructors();
        return new ResponseEntity<>(userResponses, HttpStatus.OK);
    }

}
