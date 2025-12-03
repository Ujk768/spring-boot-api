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
import java.util.stream.Collectors;

@Service
public class SearchService {
    @Autowired
    private CourseRepository courseRepository;
    private final List<Course> courses = new ArrayList<>();
    private final Map<String, Set<Integer>> invertedIndex = new ConcurrentHashMap<>();
    private static final Pattern RATING_PATTERN = Pattern.compile("^\\s*(\\d+(\\.\\d+)?).*");

    // init and buildIndex remain unchanged...

    @PostConstruct
    public void init() {
        courses.addAll(courseRepository.findAll());
        buildIndex();
    }

    private void buildIndex() {
        for (int i = 0; i < courses.size(); i++) {
            Course c = courses.get(i);
            // Null check is important here
            if (c.getCourseName() == null) continue;
            String courseName = c.getCourseName().toLowerCase();
            for (String word : courseName.split("[^a-zA-Z0-9]+")) {
                if (!word.isEmpty()) { // Ensure token is not empty
                    invertedIndex.computeIfAbsent(word, k -> new HashSet<>()).add(i);
                }
            }
        }
        System.out.println("Inverted index built with " + invertedIndex.size() + " unique tokens.");
    }

    private float parseRating(String rating) {
        if (rating == null || rating.isEmpty()) return 0.0f;
        try {
            Matcher matcher = RATING_PATTERN.matcher(rating);
            if (matcher.matches()) {
                String numericPart = matcher.group(1);
                return Float.parseFloat(numericPart);
            }
        } catch (NumberFormatException e) {
            System.err.println("Could not parse numeric part as float: " + rating);
        }
        return 0.0f;
    }


    /**
     * Searches the inverted index for all terms in the keyword phrase and returns
     * the intersection of the results.
     */
    public List<Course> search(String keyword) {
        if (keyword == null || keyword.isEmpty()) return Collections.emptyList();

        // 1. Tokenize the input phrase
        String[] searchTerms = keyword.toLowerCase().split("[^a-zA-Z0-9]+");

        // Use a Set to hold the common indices, initialized with the first word's results
        Set<Integer> intersectionIndexes = null;

        // 2. Iterate through search terms and intersect results
        for (String term : searchTerms) {
            if (term.isEmpty()) continue;

            Set<Integer> termIndexes = invertedIndex.get(term);

            if (termIndexes == null) {
                // Fallback to substring match: find any courses whose name contains the term
                termIndexes = new HashSet<>();

                for (int i = 0; i < courses.size(); i++) {
                    Course c = courses.get(i);
                    if (c.getCourseName() != null &&
                            c.getCourseName().toLowerCase().contains(term)) {
                        termIndexes.add(i);
                    }
                }

                // If STILL nothing found → return empty
                if (termIndexes.isEmpty()) {
                    return Collections.emptyList();
                }
            }

            if (intersectionIndexes == null) {
                // Initialize the set with the first term's results (e.g., all courses with "backend")
                intersectionIndexes = new HashSet<>(termIndexes);
            } else {
                // Intersect the current results with the new term's results
                // This keeps only the courses that contain ALL previous terms AND the current term.
                intersectionIndexes.retainAll(termIndexes);

                // Optimization: If the intersection becomes empty, we can stop early
                if (intersectionIndexes.isEmpty()) {
                    return Collections.emptyList();
                }
            }
        }

        if (intersectionIndexes == null || intersectionIndexes.isEmpty()) {
            return Collections.emptyList();
        }

        // 3. Collect results and sort
        List<Course> results = intersectionIndexes.stream()
                .map(courses::get)
                .collect(Collectors.toList());

        results.sort(Comparator.comparing(
                course -> parseRating(course.getRating()),
                Comparator.reverseOrder()
        ));

        return results;
    }
}