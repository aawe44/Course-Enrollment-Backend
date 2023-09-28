package com.github.aawe44.repository;

import com.github.aawe44.domain.Course;
import com.github.aawe44.domain.User;
import com.github.aawe44.domain.UserCourse;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourse/*ORM model class*/, Long/*primary key type*/> {
    Optional<UserCourse> findOneByUserAndCourse(User user, Course course);

    @Transactional
    void deleteByUserAndCourse(User user, Course course);

    List<UserCourse> findAllByUser(User user);
}
