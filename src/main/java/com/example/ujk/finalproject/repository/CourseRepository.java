package com.example.ujk.finalproject.repository;

import com.example.ujk.finalproject.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends MongoRepository<Course, String> {

}