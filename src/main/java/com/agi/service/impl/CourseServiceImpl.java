package com.agi.service.impl;

import com.agi.converter.CourseByIdConverter;
import com.agi.core.Course;
import com.agi.payload.request.CourseRequest;
import com.agi.payload.response.CourseResponse;
import com.agi.payload.response.MessageResponse;
import com.agi.repository.CourseRepository;
import com.agi.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    public List<CourseResponse> index() {
        List<Course> courses = courseRepository.findAll();
        List<CourseResponse> courseResponses = new ArrayList<>();
        for (Course course: courses){
            CourseResponse courseResponse = new CourseResponse();
            courseToCourseResponse(course, courseResponse);
            courseResponses.add(courseResponse);
        }
        return courseResponses;
    }

    public CourseResponse create(CourseRequest courseRequest) {
        Course course = new Course();
        courseRequestToCourse(course, courseRequest);
        Course newCourse = courseRepository.save(course);
        CourseResponse courseResponse = new CourseResponse();
        courseToCourseResponse(newCourse, courseResponse);
        return courseResponse;
    }

    public CourseResponse show(Long id){
        CourseByIdConverter courseByIdConverter = new CourseByIdConverter(courseRepository);
        Course course = courseByIdConverter.convert(id);
        CourseResponse courseResponse = new CourseResponse();
        if (course != null) {
            courseToCourseResponse(course, courseResponse);
        }
        return courseResponse;
    }

    public CourseResponse update(Long id, CourseRequest courseRequest){
        CourseByIdConverter courseByIdConverter = new CourseByIdConverter(courseRepository);
        Course course = courseByIdConverter.convert(id);
        CourseResponse courseResponse = new CourseResponse();
        if (course != null) {
            courseRequestToCourse(course, courseRequest);
            Course newCourse = courseRepository.save(course);
            courseToCourseResponse(newCourse, courseResponse);
        }
        return courseResponse;
    }

    @Override
    public MessageResponse delete(Long id) {
        CourseByIdConverter courseByIdConverter = new CourseByIdConverter(courseRepository);
        Course course = courseByIdConverter.convert(id);
        if (course != null) {
            courseRepository.delete(course);
        }
        return new  MessageResponse("The course is deleted successfully");
    }

    private void courseToCourseResponse(Course course, CourseResponse courseResponse){
        courseResponse.setCourse_id(course.getId());
        courseResponse.setCourse_name(course.getName());
        courseResponse.setCourse_desc(course.getDesc());
        courseResponse.setCourse_img(course.getImg());
        courseResponse.setCourse_duration(course.getDuration());
        courseResponse.setCourse_price(course.getPrice());
        courseResponse.setCourse_original_price(course.getOriginal_price());
    }

    private void courseRequestToCourse(Course course,CourseRequest courseRequest){
        course.setName(courseRequest.getCourse_name());
        course.setDesc(courseRequest.getCourse_desc());
        course.setImg(courseRequest.getCourse_img());
        course.setDuration(courseRequest.getCourse_duration());
        course.setPrice(courseRequest.getCourse_price());
        course.setOriginal_price(courseRequest.getCourse_original_price());
    }

}
