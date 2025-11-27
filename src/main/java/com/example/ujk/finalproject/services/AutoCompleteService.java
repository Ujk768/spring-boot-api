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

    private Set<String> dictionary = new HashSet<>();

    public AutoCompleteService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @PostConstruct
    public void loadDictionary() {
        List<Course> allCourses = courseRepository.findAll();
        System.out.println("Courses: " + allCourses.size());

        for (Course course : allCourses) {
            String courseName = course.getCourseName();
            if (courseName != null) {
                // Split course name into words by non-alphanumeric chars (space, colon, dash, etc.)
                String[] parts = courseName.split("[^a-zA-Z0-9]+");

                for (String c : parts) {
                    if (!c.isEmpty()) {
                        dictionary.add(c.trim().toLowerCase());
                    }
                }
            }
        }

        System.out.println("Autocomplete dictionary loaded with " + dictionary.size() + " words.");
    }

    public List<String> getCompletions(String prefix) {
        if (prefix == null || prefix.isEmpty()) return Collections.emptyList();

        String search = prefix.toLowerCase();

        // Match any word that contains the prefix, not just starts with
        return dictionary.stream()
                .filter(word -> word.contains(search))
                .limit(20)
                .collect(Collectors.toList());
    }

}
