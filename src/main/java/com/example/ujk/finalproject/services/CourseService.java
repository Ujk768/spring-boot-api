package com.example.ujk.finalproject.services;
import com.example.ujk.finalproject.model.Course;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AddFieldsOperation;
import org.springframework.data.mongodb.core.aggregation.ConvertOperators;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final MongoTemplate mongoTemplate;

    public CourseService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Course> getCoursesSortedByRating(int page, int size) {

        Aggregation aggregation = Aggregation.newAggregation(
                // convert string "Rating" → decimal
                Aggregation.addFields()
                        .addFieldWithValue("numericRating",
                                ConvertOperators.ToDouble.toDouble("$Rating"))
                        .build(),

                // sort by numeric rating
                Aggregation.sort(Sort.by(Sort.Direction.DESC, "numericRating")),

                // pagination
                Aggregation.skip((long) page * size),
                Aggregation.limit(size)
        );

        return mongoTemplate.aggregate(aggregation, "courses", Course.class)
                .getMappedResults();
    }
}
