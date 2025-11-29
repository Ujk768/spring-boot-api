package com.example.ujk.finalproject.services;

import com.example.ujk.finalproject.model.Course;
import com.example.ujk.finalproject.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SearchService {
    @Autowired
    private CourseRepository courseRepository;
    private final List<Course> courses = new ArrayList<>();
    private final Map<String, Set<Integer>> invertedIndex = new ConcurrentHashMap<>();
    private static final Pattern RATING_PATTERN = Pattern.compile("^\\s*(\\d+(\\.\\d+)?).*");

    // Load from DB after service creation
    @PostConstruct
    public void init() {
        courses.addAll(courseRepository.findAll());
        buildIndex();
    }

    private void buildIndex() {
        for (int i = 0; i < courses.size(); i++) {
            Course c = courses.get(i);
            String courseName = c.getCourseName().toLowerCase();
            if (courseName == null) continue;
            for (String word : courseName.split("[^a-zA-Z0-9]+")) {
                invertedIndex.computeIfAbsent(word, k -> new HashSet<>()).add(i);
            }
        }
    }

    private float parseRating(String rating) {
        if (rating == null || rating.isEmpty()) {
            return 0.0f;
        }

        try {
            // Create a Matcher object
            Matcher matcher = RATING_PATTERN.matcher(rating);

            // Attempt to find a match
            if (matcher.matches()) {
                // Group 1 captures the entire numeric part (e.g., "4" or "4.5")
                String numericPart = matcher.group(1);

                // Convert the extracted numeric string to a float
                return Float.parseFloat(numericPart);
            }
        } catch (NumberFormatException e) {
            // Log error if the extracted part is somehow not a valid float
            System.err.println("Could not parse numeric part as float: " + rating);
        }

        // Return 0.0f if no numeric part was found or an error occurred
        return 0.0f;
    }

    public List<Course> search(String keyword) {
        if (keyword == null || keyword.isEmpty()) return Collections.emptyList();
        keyword = keyword.toLowerCase();
        Set<Integer> indexes = invertedIndex.get(keyword);
        if (indexes == null) return Collections.emptyList();
        List<Course> results = new ArrayList<>();
        for (Integer index : indexes) {
            results.add(courses.get(index));
        }
        results.sort(Comparator.comparing(
                // Use a custom comparator that parses the rating string to a float
                course -> parseRating(course.getRating()),
                Comparator.reverseOrder() // Sort from highest rating to lowest
        ));
        return results;
    }
}


