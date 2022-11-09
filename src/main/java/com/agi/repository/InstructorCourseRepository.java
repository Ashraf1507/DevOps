package com.agi.repository;

import com.agi.core.Course;
import com.agi.core.InstructorCourse;
import com.agi.core.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface InstructorCourseRepository extends JpaRepository<InstructorCourse, Long> {
    public List<InstructorCourse> findInstructorCoursesByCourse(Course course);
    public List<InstructorCourse> findInstructorCoursesByInstructor(User instructor);
}