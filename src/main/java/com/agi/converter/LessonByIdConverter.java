package com.agi.converter;

import com.agi.core.Course;
import com.agi.core.Lesson;
import com.agi.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class LessonByIdConverter {
    private final LessonRepository lessonRepository;

    @Autowired
    public LessonByIdConverter(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public Lesson convert(Long id) {
        return lessonRepository.findById(id).orElse(null);
    }
}
