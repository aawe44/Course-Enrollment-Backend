package com.github.aawe44.service.mapper;

import com.github.aawe44.domain.Course;
import com.github.aawe44.service.dto.CourseDTO;
import org.springframework.stereotype.Service;

@Service
public class CourseMapper {

    public CourseDTO courseToCourseDTO(Course course) {
        return CourseDTO
            .builder()
            .courseName(course.getCourseName())
            .courseContent(course.getCourseContent())
            .courseLocation(course.getCourseLocation())
            .teacherID(course.getTeacherId())
            .build();
    }
}
