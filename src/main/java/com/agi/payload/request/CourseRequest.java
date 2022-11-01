package com.agi.payload.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class CourseRequest {
    @NotNull
    private String course_name;
    @NotNull
    private String course_desc;
    @NotNull
    private String course_img;
    @NotNull
    private String course_duration;
    @NotNull
    private String course_price;
    @NotNull
    private String course_original_price;
}
