package com.example.ujk.finalproject.model;
import jakarta.validation.constraints.*;
public class SpellCheckRequest {

    @NotBlank(message = "word is required")
    @Pattern(
            regexp = "^[A-Za-z\\s]+$",
            message = "word must contain only letters and spaces"
    )
    private String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
