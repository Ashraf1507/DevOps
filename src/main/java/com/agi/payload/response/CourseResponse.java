package com.agi.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class CourseResponse {
    private Long course_id;
    private String course_name;
    private String course_desc;
    private String course_img;
    private String course_duration;
    private String course_price;
    private String course_original_price;
    private List<String> course_authors;
    private List<LessonResponse> course_lessons;

}
