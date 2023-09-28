package com.github.aawe44.service;

import com.github.aawe44.domain.Course;
import com.github.aawe44.domain.User;
import com.github.aawe44.domain.UserCourse;
import com.github.aawe44.repository.CourseRepository;
import com.github.aawe44.repository.UserCourseRepository;
import com.github.aawe44.repository.UserRepository;
import com.github.aawe44.service.dto.CourseDTO;
import com.github.aawe44.service.mapper.CourseMapper;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourseService {

    private CourseRepository courseRepository;
    private CourseMapper courseMapper;

    private UserRepository userRepository;
    private UserCourseRepository userCourseRepository;

    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll(); //select * from course

        return courses.stream().map(course -> courseMapper.courseToCourseDTO(course)).collect(Collectors.toList());
    }

    public void enrollCourse(String userName, String courseName) {
        UserCourse userCourse = getUserCourse(userName, courseName);
        Optional<UserCourse> optionalUserCourse = userCourseRepository.findOneByUserAndCourse(userCourse.getUser(), userCourse.getCourse());

        optionalUserCourse.ifPresent(existingUserCourse -> {
            throw new IllegalArgumentException("userCourse already exist:" + existingUserCourse.toString());
        });

        userCourseRepository.save(userCourse);
    }

    public void dropCourse(String userName, String courseName) {
        UserCourse userCourse = getUserCourse(userName, courseName);
        userCourseRepository.deleteByUserAndCourse(userCourse.getUser(), userCourse.getCourse());
    }

    public List<CourseDTO> getStudentCourses(String userName) {
        Optional<User> optionalUser = userRepository.findOneByLogin(userName);
        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("No such user: " + userName));

        List<UserCourse> allByUser = userCourseRepository.findAllByUser(user);

        return allByUser
            .stream()
            .map(UserCourse::getCourse)
            .map(course -> courseMapper.courseToCourseDTO(course))
            .collect(Collectors.toList());
    }

    private UserCourse getUserCourse(String userName, String courseName) {
        Optional<User> optionalUser = userRepository.findOneByLogin(userName);
        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("No such user: " + userName));

        Optional<Course> optionalCourse = courseRepository.findOneByCourseName(courseName); // select * from course where course_name limit 1
        Course course = optionalCourse.orElseThrow(() -> new IllegalArgumentException("No such course: " + courseName));

        return new UserCourse(user, course);
    }
}
