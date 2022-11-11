package com.agi.payload.response;

import lombok.Data;

@Data
public class LessonResponse {
    private Long lesson_id;
    private Long course_id;
    private String lesson_name;
    private String lesson_desc;
    private String lesson_url;
}
