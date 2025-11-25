package com.example.ujk.finalproject.model;

import java.util.List;

public class AutoCompleteResponse {
    private List<String> completions;

    public AutoCompleteResponse(List<String> completions) {
        this.completions = completions;
    }

    public List<String> getCompletions() {
        return completions;
    }

    public void setCompletions(List<String> completions) {
        this.completions = completions;
    }
}