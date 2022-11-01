package com.agi.payload.response;

import lombok.Data;

@Data
public class CourseResponse {
    private Long course_id;
    private String course_name;
    private String course_desc;
    private String course_img;
    private String course_duration;
    private String course_price;
    private String course_original_price;
}
