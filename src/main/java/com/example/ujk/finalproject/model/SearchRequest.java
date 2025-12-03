package com.example.ujk.finalproject.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class SearchRequest {

    @NotBlank(message = "word is required")
    @Pattern(
            regexp = "^[A-Za-z0-9\\s\\-:/]+$",
            message = "Search term contains invalid characters"
    )
    private String search;

    public String getSearch() { return search; }
    public void setSearch(String search) { this.search = search; }
}
