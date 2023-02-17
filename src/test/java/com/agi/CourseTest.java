package com.agi;


import com.agi.payload.request.CourseRequest;
import com.agi.service.CourseService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CourseTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    Long id = 1L; String name = "hello";String desc = "hello";String duration = "hello";String price = "hello";String originalPrice = "hello";

    @DisplayName("Test a status of a none existing courses")
    @Test
    public void getExistingCourse() throws Exception {
        CourseRequest courseRequest1 = new CourseRequest();
        mockMvc.perform(get("/api/courses/2"))
                .andExpect(status().isOk());
    }


    @DisplayName("Test a status of an existing courses")
    @Test
    public void getNoneExistingCourse() throws Exception {
        mockMvc.perform(get("/api/courses/200"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("assert an Id of a course is a long and equal to 1")
    public  void assertTypeIdCourse() throws Exception {
        assertThat(id, is(1L));
    }

    @Test
    @DisplayName("assert that content is json")
    public  void assertContentIsJson() throws Exception {
        mockMvc.perform(get("/api/courses/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        mockMvc.perform(get("/api/courses"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("assert an Description of a course is a string and not null")
    public  void assertTypeDescCourse() throws Exception {
        mockMvc.perform(get("/api/courses/1"))
                .andExpect(jsonPath("desc", notNullValue()));
    }

    @Test
    @DisplayName("assert an Name of a course is a string and not null")
    public  void assertTypeNameCourse() throws Exception {
        mockMvc.perform(get("/api/courses/1"))
                .andExpect(jsonPath("name", notNullValue()));
    }

    @Test
    @DisplayName("assert an Duration of a course is a string and not null")
    public  void assertTypePriceCourse() throws Exception {
        mockMvc.perform(get("/api/courses/1"))
                .andExpect(jsonPath("price", notNullValue()));;
    }

    @Test
    @DisplayName("assert an Price of a course is a string and not null")
    public  void assertTypeDurationCourse() throws Exception {
        mockMvc.perform(get("/api/courses/1"))
                .andExpect(jsonPath("duration", notNullValue()));
    }

    @Test
    @DisplayName("assert an Original Price of a course is a string and not null")
    public  void assertTypeOriginalPriceCourse() throws Exception {
        mockMvc.perform(get("/api/courses/1"))
                .andExpect(jsonPath("originalPrice", notNullValue()));
    }

}
