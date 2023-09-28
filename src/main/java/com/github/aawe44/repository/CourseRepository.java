package com.github.aawe44.repository;

import com.github.aawe44.domain.Course;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course/*ORM model class*/, Long/*primary key type*/> {
    Optional<Course> findOneByCourseName(String courseName);
}
