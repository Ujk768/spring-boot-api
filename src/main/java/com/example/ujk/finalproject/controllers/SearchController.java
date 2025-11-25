package com.example.ujk.finalproject.controllers;


import com.example.ujk.finalproject.model.SearchRequest;
import com.example.ujk.finalproject.model.SearchResponse;
import com.example.ujk.finalproject.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/search")
@CrossOrigin(origins = "*")
public class SearchController {

    @Autowired
    private SearchService service;

    @PostMapping
    public SearchResponse search(@RequestBody SearchRequest req) {
        return new SearchResponse(service.search(req.getSearch()));
    }
}
