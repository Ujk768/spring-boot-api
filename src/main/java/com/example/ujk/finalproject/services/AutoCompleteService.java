package com.example.ujk.finalproject.services;

import com.example.ujk.finalproject.model.Course;
import com.example.ujk.finalproject.repository.CourseRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AutoCompleteService {

    private final CourseRepository courseRepository;

    // Changed from Set of words to List of full Course Names to preserve phrases
    private final List<String> courseNames = new ArrayList<>();

    public AutoCompleteService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @PostConstruct
    public void loadDictionary() {
        List<Course> allCourses = courseRepository.findAll();
        System.out.println("Courses found in DB: " + allCourses.size());

        courseNames.clear(); // Ensure clean slate on reload

        for (Course course : allCourses) {
            String name = course.getCourseName();
            if (name != null && !name.isEmpty()) {
                // Store the FULL name, trimmed.
                // We do NOT split by space here.
                courseNames.add(name.trim());
            }
        }

        System.out.println("Autocomplete loaded with " + courseNames.size() + " course titles.");
    }

    public List<String> getCompletions(String prefix) {
        if (prefix == null || prefix.isEmpty()) return Collections.emptyList();

        String search = prefix.toLowerCase();

        return courseNames.stream()
                // Check if the FULL course name contains the search phrase
                .filter(name -> name.toLowerCase().contains(search))
                // Optional: Sort by length so shorter (more exact) matches appear first
                .sorted(Comparator.comparingInt(String::length))
                .limit(20)
                .collect(Collectors.toList());
    }
}