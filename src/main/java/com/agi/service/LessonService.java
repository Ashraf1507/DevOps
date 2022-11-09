package com.agi.service;

import com.agi.payload.request.LessonRequest;
import com.agi.payload.response.LessonResponse;
import com.agi.payload.response.MessageResponse;

import java.util.List;

public interface LessonService {
    public List<LessonResponse> index(Long course_id);

    public LessonResponse show(Long lesson_id);
    public LessonResponse edit(Long lesson_id, LessonRequest lessonRequest);
    public LessonResponse create(Long course_id, LessonRequest lessonRequest);
    public MessageResponse delete(Long lesson_id);
}
