package com.github.aawe44.web.rest;

import com.github.aawe44.security.SecurityUtils;
import com.github.aawe44.service.CourseService;
import com.github.aawe44.service.dto.CourseDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CourseController {

    private CourseService courseService;

    /**
     * 1. Requirement: list all courses
     * 2. Http Method:GET
     * 3. URL: /allcourses
     * 4. HTTP status code: 200
     * 5: Request Body: None
     * 6: Response Body: List<CourseDTO>
     * 7. Request Header: JWT Token
     */
    @GetMapping(path = "/allcourses")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<CourseDTO> getAllCourses() {
        return this.courseService.getAllCourses();
    }

    /**
     * 1. Requirement: student enroll course
     * 2. Http Method: POST
     * 3. URL: /student/course/{courseName}
     * 4. HTTP status code: 201 (creation succeeded)
     * 5: Request Body: path variable -{courseName}
     * 6: Response Body: None (Void)
     * 7. Request Header: JWT Token
     */
    @PostMapping(path = "/student/course/{courseName}")
    @ResponseStatus(HttpStatus.CREATED)
    public void enrollCourse(@PathVariable String courseName) {
        String userName = getUserName();
        String decodedPath = UriUtils.decode(courseName, "UTF-8");

        this.courseService.enrollCourse(userName, decodedPath);
    }

    /**
     * 1. Requirement: get enrolled courses
     * 2. Http Method:GET
     * 3. URL: /student/courses
     * 4. HTTP status code: 200
     * 5: Request Body: None
     * 6: Response Body: List<CourseDTO>
     * 7. Request Header: JWT Token
     */
    @GetMapping(path = "/student/courses")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<CourseDTO> getStudentCourses() {
        String userName = getUserName();

        return this.courseService.getStudentCourses(userName);
    }

    /**
     * 1. Requirement: drop course
     * 2. Http Method: DELETE
     * 3. URL: /student/course/{courseName}
     * 4. HTTP status code: 204
     * 5: Request Body: path variable -{courseName}
     * 6: Response Body: None (Void)
     * 7. Request Header: JWT Token
     */
    @DeleteMapping(path = "/student/course/{courseName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void dropCourse(@PathVariable String courseName) {
        String userName = getUserName();
        this.courseService.dropCourse(userName, courseName);
    }

    /**
     * Extract usernaem from JWT token
     */
    private String getUserName() {
        return SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}
