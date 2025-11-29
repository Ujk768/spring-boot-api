package com.example.ujk.finalproject.model;

import java.util.List;
import java.util.Map;

public class TotalFrequencyResponse {
    private int statusCode;
    private String message;
    private List<Map.Entry<String, Integer>> frequencyMap;
    public List<Map.Entry<String, Integer>> getFrequencyMap() {
        return frequencyMap;
    }
    public void setFrequencyMap(List<Map.Entry<String, Integer>> frequencyMap) {
        this.frequencyMap = frequencyMap;
    }
    public int getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
