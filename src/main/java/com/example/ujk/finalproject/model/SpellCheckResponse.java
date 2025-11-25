package com.example.ujk.finalproject.model;

public class SpellCheckResponse {
    private String correctedWord;

    public SpellCheckResponse(String correctedWord) {
        this.correctedWord = correctedWord;
    }

    public String getCorrectedWord() {
        return correctedWord;
    }

    public void setCorrectedWord(String correctedWord) {
        this.correctedWord = correctedWord;
    }
}