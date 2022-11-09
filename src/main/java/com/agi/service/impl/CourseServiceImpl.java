package com.agi.service.impl;

import com.agi.converter.CourseByIdConverter;
import com.agi.converter.UserByIdConverter;
import com.agi.core.Course;
import com.agi.core.InstructorCourse;
import com.agi.core.StudentCourse;
import com.agi.core.user.User;
import com.agi.payload.request.CourseRequest;
import com.agi.payload.response.CourseResponse;
import com.agi.payload.response.MessageResponse;
import com.agi.repository.CourseRepository;
import com.agi.repository.InstructorCourseRepository;
import com.agi.repository.StudentCourseRepository;
import com.agi.repository.UserRepository;
import com.agi.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    InstructorCourseRepository instructorCourseRepository;

    @Autowired
    StudentCourseRepository studentCourseRepository;

    @Autowired
    UserRepository userRepository;


    public List<CourseResponse> index() {
        List<Course> courses = courseRepository.findAll();
        List<CourseResponse> courseResponses = new ArrayList<>();
        for (Course course : courses) {
            CourseResponse courseResponse = new CourseResponse();
            courseToCourseResponse(course, courseResponse);
            courseResponses.add(courseResponse);
        }
        return courseResponses;
    }

    public List<CourseResponse> indexByInstructor(Long id) {
        List<CourseResponse> courseResponses = new ArrayList<>();
        UserByIdConverter userByIdConverter = new UserByIdConverter(userRepository);
        User instructor = userByIdConverter.convert(id);
        List<InstructorCourse> instructorCourses = instructorCourseRepository.findInstructorCoursesByInstructor(instructor);
        for (InstructorCourse instructorCourse : instructorCourses) {
            CourseResponse courseResponse = new CourseResponse();
            courseToCourseResponse(instructorCourse.getCourse(), courseResponse);
            courseResponses.add(courseResponse);
        }
        return courseResponses;
    }

    public List<CourseResponse> indexByStudent(Long id) {
        List<CourseResponse> courseResponses = new ArrayList<>();
        UserByIdConverter userByIdConverter = new UserByIdConverter(userRepository);
        User student = userByIdConverter.convert(id);
        List<StudentCourse> studentCourses = studentCourseRepository.findStudentCoursesByStudent(student);
        for (StudentCourse studentCourse : studentCourses) {
            CourseResponse courseResponse = new CourseResponse();
            courseToCourseResponse(studentCourse.getCourse(), courseResponse);
            courseResponses.add(courseResponse);
        }
        return courseResponses;
    }

    public MessageResponse addCourseToStudent(Long course_id, Long student_id) {
        UserByIdConverter userByIdConverter = new UserByIdConverter(userRepository);
        User student = userByIdConverter.convert(student_id);
        CourseByIdConverter courseByIdConverter = new CourseByIdConverter(courseRepository);
        Course course = courseByIdConverter.convert(course_id);
        studentCourseRepository.save(new StudentCourse(student, course));
        return new MessageResponse("Added Course To Student");
    }


    public CourseResponse create(CourseRequest courseRequest, Long id) {
        Course course = new Course();
        courseRequestToCourse(course, courseRequest);
        Course newCourse = courseRepository.save(course);
        setInstructorsToTheirCourse(newCourse, courseRequest, id);
        CourseResponse courseResponse = new CourseResponse();
        courseToCourseResponse(newCourse, courseResponse);
        return courseResponse;
    }

    public CourseResponse show(Long id) {
        CourseByIdConverter courseByIdConverter = new CourseByIdConverter(courseRepository);
        Course course = courseByIdConverter.convert(id);
        CourseResponse courseResponse = new CourseResponse();
        if (course != null) {
            courseToCourseResponse(course, courseResponse);
        }
        return courseResponse;
    }

    public CourseResponse update(Long id, CourseRequest courseRequest) {
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
            List<StudentCourse> studentCourses = studentCourseRepository.findStudentCoursesByCourse(course);
            System.out.println("tbn");
            System.out.println(studentCourses);
            if (studentCourses.isEmpty()) {
                List<InstructorCourse> instructorCourses = instructorCourseRepository.findInstructorCoursesByCourse(course);
                instructorCourseRepository.deleteAll(instructorCourses);
                courseRepository.delete(course);
                return new MessageResponse("Course deleted successfully");
            }
            else {
                return new MessageResponse("Course not deleted");
            }
        } else {
            return new MessageResponse("Course not found");
        }
    }

    private void courseToCourseResponse(Course course, CourseResponse courseResponse) {
        courseResponse.setCourse_id(course.getId());
        courseResponse.setCourse_name(course.getName());
        courseResponse.setCourse_desc(course.getDesc());
        courseResponse.setCourse_img(course.getImg());
        courseResponse.setCourse_duration(course.getDuration());
        courseResponse.setCourse_price(course.getPrice());
        courseResponse.setCourse_original_price(course.getOriginal_price());
        List<InstructorCourse> instructorCourses = instructorCourseRepository.findInstructorCoursesByCourse(course);
        List<String> authors = new ArrayList<>();
        for (InstructorCourse instructorCourse : instructorCourses) {
            authors.add(instructorCourse.getInstructor().getUsername());
        }
        courseResponse.setCourse_authors(authors);
    }

    private void courseRequestToCourse(Course course, CourseRequest courseRequest) {
        course.setName(courseRequest.getCourse_name());
        course.setDesc(courseRequest.getCourse_desc());
        course.setImg(courseRequest.getCourse_img());
        course.setDuration(courseRequest.getCourse_duration());
        course.setPrice(courseRequest.getCourse_price());
        course.setOriginal_price(courseRequest.getCourse_original_price());
    }

    private void setInstructorsToTheirCourse(Course course, CourseRequest courseRequest, Long id) {
        UserByIdConverter userByIdConverter = new UserByIdConverter(userRepository);
        User instructor = userByIdConverter.convert(id);
        instructorCourseRepository.save(new InstructorCourse(instructor, course));

    }

}
