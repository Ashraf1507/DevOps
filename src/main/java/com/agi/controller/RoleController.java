package com.agi.controller;

import com.agi.payload.response.RoleResponse;
import com.agi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleResponse>> index(){
        List<RoleResponse> roleResponses = roleService.index();
        return new ResponseEntity<>(roleResponses, HttpStatus.ACCEPTED);
    }
}
