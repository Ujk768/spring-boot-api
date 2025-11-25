package com.example.ujk.finalproject.model;

import java.util.List;

public class SearchResponse {


    private List<Course> courses_found;

    public SearchResponse(List<Course> courses_found) {
        this.courses_found = courses_found;
    }

    public List<Course> getCourses_found() {
        return courses_found;
    }
}
