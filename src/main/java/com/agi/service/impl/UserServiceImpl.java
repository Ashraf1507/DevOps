package com.agi.service.impl;

import com.agi.converter.UserByIdConverter;
import com.agi.core.Course;
import com.agi.core.InstructorCourse;
import com.agi.core.StudentCourse;
import com.agi.core.user.EnumRole;
import com.agi.core.user.Role;
import com.agi.core.user.User;
import com.agi.payload.response.CourseResponse;
import com.agi.payload.response.MessageResponse;
import com.agi.payload.response.ProfileResponse;
import com.agi.payload.response.UserResponse;
import com.agi.repository.InstructorCourseRepository;
import com.agi.repository.RoleRepository;
import com.agi.repository.StudentCourseRepository;
import com.agi.repository.UserRepository;
import com.agi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    StudentCourseRepository studentCourseRepository;

    @Autowired
    InstructorCourseRepository instructorCourseRepository;

    public List<UserResponse> indexStudents() {
        List<User> users = userRepository.findUsersByRolesId(3L);
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user: users) {
            UserResponse userResponse = new UserResponse();
            userToUserResponse(user, userResponse);
            userResponses.add(userResponse);
        }
        return userResponses;
    }

    @Override
    public List<UserResponse> indexInstructors() {
        List<User> users = userRepository.findUsersByRolesId(2L);
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user: users) {
            UserResponse userResponse = new UserResponse();
            userToUserResponse(user, userResponse);
            userResponses.add(userResponse);
        }
        return userResponses;
    }

    @Override
    public ProfileResponse show(Long id) {
        UserByIdConverter userByIdConverter = new UserByIdConverter(userRepository);
        User user = userByIdConverter.convert(id);
        ProfileResponse profileResponse = new ProfileResponse();
        userToProfileResponse(user, profileResponse);
        List<CourseResponse> courseResponses = new ArrayList<>();
        boolean isStudent = false;
        for (Role role: user.getRoles()){
            if(role.getName()==EnumRole.ROLE_STUDENT){
                isStudent = true;
                break;
            }
        }
        if(isStudent){
            List<String> roles = new ArrayList<>();
            roles.add("ROLE_STUDENT");
            profileResponse.setRoles(roles);
            List<StudentCourse> studentCourses = studentCourseRepository.findStudentCoursesByStudent(user);
            for (StudentCourse studentCourse : studentCourses) {
                CourseResponse courseResponse = new CourseResponse();
                courseToCourseResponse(studentCourse.getCourse(), courseResponse);
                courseResponses.add(courseResponse);
            }
        }
        else {
            List<String> roles = new ArrayList<>();
            roles.add("ROLE_INSTRUCTOR");
            profileResponse.setRoles(roles);
            List<InstructorCourse> instructorCourses = instructorCourseRepository.findInstructorCoursesByInstructor(user);
            for (InstructorCourse instructorCourse : instructorCourses){
                CourseResponse courseResponse = new CourseResponse();
                courseToCourseResponse(instructorCourse.getCourse(), courseResponse);
                courseResponses.add(courseResponse);
            }
        }
        profileResponse.setCourses(courseResponses);
        return profileResponse;
    }

    @Override
    public MessageResponse delete(Long id) {
        UserByIdConverter userByIdConverter = new UserByIdConverter(userRepository);
        User user = userByIdConverter.convert(id);
        boolean isStudent = false;
        for (Role role: user.getRoles()){
            if(role.getName()==EnumRole.ROLE_STUDENT){
                isStudent = true;
                break;
            }
        }
        if (isStudent){
            List<StudentCourse> studentCourses = studentCourseRepository.findStudentCoursesByStudent(user);
            studentCourseRepository.deleteAll(studentCourses);
        } else {
            List<InstructorCourse> instructorCourses = instructorCourseRepository.findInstructorCoursesByInstructor(user);
            instructorCourseRepository.deleteAll(instructorCourses);
        }
        userRepository.delete(user);
        return new MessageResponse("User Deleted Successfully");
    }

    public void userToUserResponse(User user, UserResponse userResponse){
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());
        userResponse.setId(user.getId());
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_STUDENT");
        userResponse.setRoles(roles);
    }

    public void userToProfileResponse(User user, ProfileResponse profileResponse){
        profileResponse.setUsername(user.getUsername());
        profileResponse.setEmail(user.getEmail());
        profileResponse.setId(user.getId());
    }

    private void courseToCourseResponse(Course course, CourseResponse courseResponse) {
        courseResponse.setCourse_id(course.getId());
        courseResponse.setCourse_name(course.getName());
        courseResponse.setCourse_desc(course.getDesc());
        courseResponse.setCourse_img(course.getImg());
        courseResponse.setCourse_duration(course.getDuration());
        courseResponse.setCourse_price(course.getPrice());
        courseResponse.setCourse_original_price(course.getOriginal_price());
    }
}
