package com.agi.service;

import com.agi.payload.request.CourseRequest;
import com.agi.payload.response.CourseResponse;
import com.agi.payload.response.MessageResponse;

import java.util.List;

public interface CourseService {

    List<CourseResponse> index();
    List<CourseResponse> indexByInstructor(Long id);
    List<CourseResponse> indexByStudent(Long id);

    MessageResponse addCourseToStudent(Long course_id, Long student_id);
    CourseResponse create(CourseRequest courseRequest, Long id);
    CourseResponse show(Long id);
    CourseResponse update(Long id, CourseRequest courseRequest);
    MessageResponse delete(Long id);

}
