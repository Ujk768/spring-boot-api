package com.example.ujk.finalproject.model;

public class FrequencyCountResponse {
    private String word;
    private int frequency;

    public FrequencyCountResponse(String word, int frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    public String getWord() {
        return word;
    }
    public int getFrequency() {
        return frequency;
    }
}
