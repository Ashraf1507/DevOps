package com.agi.repository;

import com.agi.core.Course;
import com.agi.core.InstructorCourse;
import com.agi.core.StudentCourse;
import com.agi.core.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {
    public List<StudentCourse> findStudentCoursesByCourse(Course course);
    public List<StudentCourse> findStudentCoursesByStudent(User student);
}