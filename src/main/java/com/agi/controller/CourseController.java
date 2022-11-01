package com.agi.controller;

import com.agi.Utils.AppConstants;
import com.agi.core.Course;
import com.agi.core.user.EnumRole;
import com.agi.core.user.User;
import com.agi.payload.request.CourseRequest;
import com.agi.payload.response.CourseResponse;
import com.agi.payload.response.MessageResponse;
import com.agi.payload.response.PagedResponse;
import com.agi.service.CourseService;
import com.agi.service.UserDetailsImpl;
import jdk.jfr.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseResponse>> index(){
        List<CourseResponse> courseResponses = courseService.index();
        return new ResponseEntity<>(courseResponses, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole(\"ROLE_INSTRUCTOR\")")
    public ResponseEntity<CourseResponse> create(@Valid @RequestBody CourseRequest courseRequest, @AuthenticationPrincipal UserDetailsImpl userDetails){
        CourseResponse courseResponse = courseService.create(courseRequest);
        return new ResponseEntity<>(courseResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> show(@PathVariable Long id){
        CourseResponse courseResponse = courseService.show(id);
        return new ResponseEntity<>(courseResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole(\"ROLE_INSTRUCTOR\")")
    public ResponseEntity<CourseResponse> show(@PathVariable Long id, @Valid @RequestBody CourseRequest courseRequest){
        CourseResponse courseResponse = courseService.update(id, courseRequest);
        return new ResponseEntity<>(courseResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole(\"ROLE_INSTRUCTOR\") or hasRole(\"ROLE_ADMIN\")")
    public ResponseEntity<MessageResponse> delete(@PathVariable Long id){
        MessageResponse messageResponse = courseService.delete(id);
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }
}
