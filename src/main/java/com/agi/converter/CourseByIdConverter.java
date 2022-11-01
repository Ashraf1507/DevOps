package com.agi.converter;

import com.agi.core.Course;
import com.agi.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CourseByIdConverter implements Converter<Long, Course> {
    private final CourseRepository courseRepository;
    @Autowired
    public CourseByIdConverter(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course convert(Long id) {
        return courseRepository.findById(id).orElse(null);
    }
}
