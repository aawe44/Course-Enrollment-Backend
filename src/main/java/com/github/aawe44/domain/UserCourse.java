package com.github.aawe44.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_course")
@Data
@NoArgsConstructor
public class UserCourse {

    public UserCourse(User user, Course course) {
        this.user = user;
        this.course = course;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto-increment
    private Long id;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User user;

    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @ManyToOne
    private Course course;
}
