package com.agi.payload.request;


import com.agi.core.Lesson;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LessonRequest {
    @NotNull
    private String lesson_name;
    @NotNull
    private String lesson_desc;
    @NotNull
    private String lesson_url;
}
