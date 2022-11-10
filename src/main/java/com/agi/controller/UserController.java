package com.agi.controller;

import com.agi.payload.response.MessageResponse;
import com.agi.payload.response.ProfileResponse;
import com.agi.payload.response.UserResponse;
import com.agi.repository.UserRepository;
import com.agi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/admin/students")
    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    public ResponseEntity<List<UserResponse>> indexStudents(){
        List<UserResponse> userResponses = userService.indexStudents();
        return new ResponseEntity<>(userResponses, HttpStatus.OK);
    }

    @GetMapping("/admin/instructors")
    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    public ResponseEntity<List<UserResponse>> indexInstructors(){
        List<UserResponse> userResponses = userService.indexInstructors();
        return new ResponseEntity<>(userResponses, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ProfileResponse> show(@PathVariable Long id){
        ProfileResponse profileResponse = userService.show(id);
        return new ResponseEntity<>(profileResponse, HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    public ResponseEntity<MessageResponse> delete(@PathVariable Long id){
        MessageResponse messageResponse = userService.delete(id);
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }
}
