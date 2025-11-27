package com.example.ujk.finalproject.services;

import com.example.ujk.finalproject.model.Course;
import com.example.ujk.finalproject.repository.CourseRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SpellCheckService {
    private final CourseRepository courseRepository;

    // This will hold all unique tokens extracted from DB
    private Set<String> vocabulary = new HashSet<>();

    public SpellCheckService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @PostConstruct
    public void loadVocabulary() {
        List<Course> allCourses = courseRepository.findAll();

        for (Course course : allCourses) {

            // Combine course fields
            String data = (course.getCourseName() + " "
                    + course.getCategory() + " "
                    + course.getSubjects())
                    .toLowerCase();

            // Tokenize by non-alphanumeric characters
            String[] words = data.split("[^a-z0-9]+");

            for (String word : words) {
                if (word.length() > 1) {          // ignore single letters
                    vocabulary.add(word);
                }
            }
        }

        System.out.println("SpellCheck vocabulary size: " + vocabulary.size());
    }

    // Returns closest match from vocabulary
    public String getCorrectedWord(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        String bestMatch = null;
        int minDistance = Integer.MAX_VALUE;

        for (String word : vocabulary) {
            int distance = levenshteinDistance(input.toLowerCase(), word);

            if (distance < minDistance) {
                minDistance = distance;
                bestMatch = word;
            }
        }

        return bestMatch;
    }

    // Check exact match
    public boolean isCorrectlySpelt(String word) {
        return vocabulary.contains(word.toLowerCase());
    }

    // Simple Levenshtein distance
    private int levenshteinDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) dp[i][0] = i;
        for (int j = 0; j <= b.length(); j++) dp[0][j] = j;

        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(
                            dp[i - 1][j - 1],
                            Math.min(dp[i - 1][j], dp[i][j - 1])
                    );
                }
            }
        }
        return dp[a.length()][b.length()];
    }
}