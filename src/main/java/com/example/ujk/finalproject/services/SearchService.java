package com.example.ujk.finalproject.services;

import com.example.ujk.finalproject.model.Course;
import com.example.ujk.finalproject.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SearchService {

    @Autowired
    private CourseRepository courseRepository;

    private final List<Course> courses = new ArrayList<>();
    private final Map<String, Set<Integer>> invertedIndex = new ConcurrentHashMap<>();


    // Load from DB after service creation
    @PostConstruct
    public void init() {
        courses.addAll(courseRepository.findAll());
        buildIndex();
    }

    private void buildIndex() {
        for (int i = 0; i < courses.size(); i++) {
            Course c = courses.get(i);

            String content = (
                    c.getCourseName() + " " +
                            c.getCategory() + " " +
                            c.getSubjects()
            ).toLowerCase();

            for (String word : content.split("\\W+")) {
                invertedIndex
                        .computeIfAbsent(word, k -> new HashSet<>())
                        .add(i);
            }
        }
    }

    public List<Course> search(String keyword) {
        if (keyword == null || keyword.isEmpty())
            return Collections.emptyList();

        keyword = keyword.toLowerCase();

        Set<Integer> indexes = invertedIndex.get(keyword);
        if (indexes == null) return Collections.emptyList();

        List<Course> results = new ArrayList<>();
        for (Integer index : indexes) {
            results.add(courses.get(index));
        }

        return results;
    }
}
