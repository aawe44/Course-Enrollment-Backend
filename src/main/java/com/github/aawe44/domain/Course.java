package com.github.aawe44.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "course")
@Data
public class Course {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto-increment
    private Long id;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_location")
    private String courseLocation;

    @Column(name = "course_content")
    private String courseContent;

    @Column(name = "teacher_id")
    private Long teacherId;
}
