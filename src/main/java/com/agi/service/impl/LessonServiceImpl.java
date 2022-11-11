package com.agi.service.impl;

import com.agi.converter.CourseByIdConverter;
import com.agi.converter.LessonByIdConverter;
import com.agi.core.Course;
import com.agi.core.Lesson;
import com.agi.payload.request.LessonRequest;
import com.agi.payload.response.LessonResponse;
import com.agi.payload.response.MessageResponse;
import com.agi.repository.CourseRepository;
import com.agi.repository.LessonRepository;
import com.agi.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    CourseRepository courseRepository;

    public List<LessonResponse> index(Long course_id) {
        CourseByIdConverter courseByIdConverter = new CourseByIdConverter(courseRepository);
        Course course = courseByIdConverter.convert(course_id);
        List<Lesson> lessons = lessonRepository.findAllByCourse(course);
        List<LessonResponse> lessonResponses = new ArrayList<>();
        for (Lesson lesson: lessons){
            LessonResponse lessonResponse = new LessonResponse();
            lessonToLessonResponse(lesson, lessonResponse);
            lessonResponse.setCourse_id(course_id);
            lessonResponses.add(lessonResponse);
        }
        return lessonResponses;
    }

    public LessonResponse show(Long lesson_id) {
        LessonByIdConverter lessonByIdConverter = new LessonByIdConverter(lessonRepository);
        Lesson lesson = lessonByIdConverter.convert(lesson_id);
        LessonResponse lessonResponse = new LessonResponse();
        lessonToLessonResponse(lesson, lessonResponse);
        return lessonResponse;
    }

    public LessonResponse edit(Long lesson_id, LessonRequest lessonRequest) {
        LessonByIdConverter lessonByIdConverter = new LessonByIdConverter(lessonRepository);
        Lesson lesson = lessonByIdConverter.convert(lesson_id);
        lessonRequestToLesson(lesson, lessonRequest);
        Lesson lessonNew = lessonRepository.save(lesson);
        LessonResponse lessonResponse = new LessonResponse();
        lessonToLessonResponse(lessonNew, lessonResponse);
        return lessonResponse;
    }

    @Override
    public LessonResponse create(Long course_id, LessonRequest lessonRequest) {
        CourseByIdConverter courseByIdConverter = new CourseByIdConverter(courseRepository);
        Course course = courseByIdConverter.convert(course_id);
        Lesson lesson = new Lesson();
        System.out.println(lessonRequest.getLesson_name());
        lessonRequestToLesson(lesson, lessonRequest);
        lesson.setCourse(course);
        Lesson lessonNew = lessonRepository.save(lesson);
        LessonResponse lessonResponse = new LessonResponse();
        lessonToLessonResponse(lessonNew, lessonResponse);
        return lessonResponse;
    }

    @Override
    public MessageResponse delete(Long lesson_id) {
        LessonByIdConverter lessonByIdConverter = new LessonByIdConverter(lessonRepository);
        Lesson lesson = lessonByIdConverter.convert(lesson_id);
        lessonRepository.delete(lesson);
        return new MessageResponse("Lesson Deleted Successfully");
    }

    private void lessonToLessonResponse(Lesson lesson, LessonResponse lessonResponse){
        lessonResponse.setLesson_id(lesson.getId());
        lessonResponse.setLesson_name(lesson.getName());
        lessonResponse.setLesson_desc(lesson.getDesc());
        lessonResponse.setLesson_url(lesson.getUrl());
        CourseByIdConverter courseByIdConverter = new CourseByIdConverter(courseRepository);
        Course course = courseByIdConverter.convert(lesson.getCourse().getId());
        assert course != null;
        lessonResponse.setCourse_id(course.getId());
    }

    private void lessonRequestToLesson(Lesson lesson, LessonRequest lessonRequest){
        lesson.setName(lessonRequest.getLesson_name());
        lesson.setUrl(lessonRequest.getLesson_url());
        lesson.setDesc(lessonRequest.getLesson_desc());
    }
}
