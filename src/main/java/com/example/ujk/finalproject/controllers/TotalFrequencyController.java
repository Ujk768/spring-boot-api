package com.example.ujk.finalproject.controllers;


import com.example.ujk.finalproject.model.TotalFrequencyResponse;
import com.example.ujk.finalproject.services.CourseService;
import com.example.ujk.finalproject.services.FrequencyCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/all-freq")
@CrossOrigin(origins = "*")
public class TotalFrequencyController {
    @Autowired
    private FrequencyCountService service;

    public TotalFrequencyController( FrequencyCountService service) {
        this.service = service;
    }
    @GetMapping
    public TotalFrequencyResponse getTotalFrequency() {
        List<Map.Entry<String, Integer>> mp = service.getSortedFrequencies();
        TotalFrequencyResponse totalFrequencyResponse = new TotalFrequencyResponse();
        totalFrequencyResponse.setFrequencyMap(mp);
        totalFrequencyResponse.setStatusCode(200);
        totalFrequencyResponse.setMessage("Success");
        return totalFrequencyResponse;
    }
}
