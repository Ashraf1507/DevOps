package com.agi.repository;

import com.agi.core.Course;
import com.agi.core.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    public List<Lesson> findAllByCourse(Course course);
}