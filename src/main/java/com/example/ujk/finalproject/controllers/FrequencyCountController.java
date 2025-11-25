package com.example.ujk.finalproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.ujk.finalproject.model.FrequencyCountRequest;
import com.example.ujk.finalproject.model.FrequencyCountResponse;
import com.example.ujk.finalproject.services.FrequencyCountService;

@RestController
@RequestMapping("/api/freq")
@CrossOrigin(origins = "*")
public class FrequencyCountController {
    @Autowired
    private FrequencyCountService service;

    @PostMapping
    public FrequencyCountResponse getFrequency(@RequestBody FrequencyCountRequest request) {

        String word = request.getWord();
        int updatedFrequency = service.incrementAndGetFrequency(word);

        return new FrequencyCountResponse(word, updatedFrequency);
    }
}
