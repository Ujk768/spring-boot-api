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

    // Vocabulary for single-word spell checking
    private final Set<String> vocabulary = new HashSet<>();

    // Official phrases for whole course validation
    private final Set<String> officialPhrases = new HashSet<>();

    public SpellCheckService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @PostConstruct
    public void loadVocabulary() {
        List<Course> allCourses = courseRepository.findAll();
        vocabulary.clear();
        officialPhrases.clear();

        for (Course course : allCourses) {
            String courseName = course.getCourseName();
            if (courseName != null && !courseName.isEmpty()) {
                officialPhrases.add(courseName.toLowerCase().trim());
            }

            String data = (courseName + " " + course.getCategory() + " " + course.getSubjects())
                    .toLowerCase();

            String[] words = data.split("[^a-z0-9]+");
            for (String word : words) {
                if (word.length() > 1) vocabulary.add(word);
            }
        }

        System.out.println("SpellCheck vocabulary size: " + vocabulary.size());
        System.out.println("Official phrases loaded: " + officialPhrases.size());
    }

    // ---------------- PHRASE VALIDATION ----------------
    public boolean isOfficialPhrase(String phrase) {
        if (phrase == null || phrase.isEmpty()) return false;
        return officialPhrases.contains(phrase.toLowerCase().trim());
    }

    // ---------------- SPELL CHECK WITH ERROR ----------------
    /**
     * Returns the corrected word if found, otherwise throws an exception.
     */
    public String getCorrectedWordOrThrow(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty");
        }

        String corrected = getCorrectedWord(input);

        if (corrected.equalsIgnoreCase(input)) {
            throw new IllegalArgumentException("No close match found for: " + input);
        }

        return corrected;
    }

    /**
     * Corrects each token individually.
     */
    public String getCorrectedWord(String input) {
        String[] tokens = input.toLowerCase().split("[^a-z0-9]+");
        StringBuilder result = new StringBuilder();
        String originalInput = input.toLowerCase();
        int index = 0;

        for (String token : tokens) {
            if (token.isEmpty()) continue;
            String correctedToken = findBestMatchForToken(token);

            int tokenStartIndex = originalInput.indexOf(token, index);
            if (tokenStartIndex > index) {
                result.append(originalInput.substring(index, tokenStartIndex));
            }

            result.append(correctedToken);
            index = tokenStartIndex + token.length();
        }

        if (index < originalInput.length()) {
            result.append(originalInput.substring(index));
        }

        return result.toString();
    }

    /**
     * Finds closest match in vocabulary using Levenshtein distance
     * with a dynamic threshold based on word length.
     */
    private String findBestMatchForToken(String token) {
        if (vocabulary.contains(token)) return token;

        int maxDistance = token.length() <= 4 ? 1 : token.length() <= 8 ? 2 : 3;

        String bestMatch = token;
        int minDistance = maxDistance + 1;

        for (String word : vocabulary) {
            int distance = levenshteinDistance(token, word);
            if (distance < minDistance) {
                minDistance = distance;
                bestMatch = word;
            }
        }

        return minDistance <= maxDistance ? bestMatch : token;
    }

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

    /**
     * Checks if a single word token is correctly spelled.
     */
    public boolean isCorrectlySpelt(String word) {
        return vocabulary.contains(word.toLowerCase());
    }
}
