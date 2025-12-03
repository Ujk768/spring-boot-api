package com.example.ujk.finalproject.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class AutoCompleteRequest {

    @NotBlank(message = "word is required")
    @Pattern(
            regexp = "^[A-Za-z0-9\\s\\-:/]+$",
            message = "word contains invalid characters"
    )
    private String word;
    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }
}