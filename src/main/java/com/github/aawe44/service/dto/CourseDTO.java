package com.github.aawe44.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseDTO {

    private String courseName;
    private String courseContent;
    private String courseLocation;
    private Long teacherID;
}
