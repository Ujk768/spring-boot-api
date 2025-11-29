package com.example.ujk.finalproject.services;


import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class FrequencyCountService {
    private final ConcurrentHashMap<String, Integer> frequencyMap = new ConcurrentHashMap<>();

    public int incrementAndGetFrequency(String word) {
        return frequencyMap.merge(word.toLowerCase(), 1, Integer::sum);
    }

    public List<Map.Entry<String, Integer>> getSortedFrequencies() {
        if (frequencyMap.isEmpty()) {
            return Collections.emptyList();
        }

        // 1. Get the entry set from the HashMap
        return frequencyMap.entrySet().stream()
                // 2. Sort the entries
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                // 3. Collect the sorted results into a List
                .collect(Collectors.toList());
    }
}
