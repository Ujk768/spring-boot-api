package com.example.ujk.finalproject.controllers;

import com.example.ujk.finalproject.model.Course;
import com.example.ujk.finalproject.model.CourseResponse;
import com.example.ujk.finalproject.services.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*")   // If frontend uses React/Vite
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public CourseResponse getAllCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<Course> courses = courseService.getCoursesSortedByRating(page, size);

        CourseResponse response = new CourseResponse();
        response.setCourses(courses);
        response.setStatusCode(200);
        response.setMessage("Courses fetched successfully");
        response.setPage(page);

        return response;
    }
}
