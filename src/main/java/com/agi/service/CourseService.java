package com.agi.service;

import com.agi.core.Course;
import com.agi.payload.request.CourseRequest;
import com.agi.payload.response.CourseResponse;
import com.agi.payload.response.MessageResponse;
import com.agi.payload.response.PagedResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CourseService {

    List<CourseResponse> index();
    CourseResponse create(CourseRequest courseRequest);
    CourseResponse show(Long id);
    CourseResponse update(Long id, CourseRequest courseRequest);
    MessageResponse delete(Long id);

}
