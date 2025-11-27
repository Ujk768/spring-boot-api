package com.example.ujk.finalproject.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "courses")
public class Course {

    @Id
    private String id;

    @Field("Course Name")
    private String courseName;

    @Field("Category")
    private String category;

    @Field("Subjects")
    private String subjects;

    @Field("Certifications")
    private String certifications;

    @Field("Pricing Type")
    private String pricingType;

    @Field("Price Details")
    private String priceDetails;

    @Field("Course Format")
    private String courseFormat;

    @Field("Special Features")
    private String specialFeatures;

    @Field("Rating")
    private String rating;

    @Field("Review Count")
    private String reviewCount;

    @Field("Review Summary")
    private String reviewSummary;

    @Field("Source Website")
    private String sourceWebsite;

    @Field("Course URL")
    private String courseURL;

    @Field("Duration")
    private String duration;

    @Field("Difficulty Level")
    private String difficultyLevel;

    @Field("Language")
    private String language;

    @Field("Subtitles Available")
    private String subtitlesAvailable;

    @Field("Prerequisites")
    private String prerequisites;

    @Field("Instructor")
    private String instructor;

    @Field("Instructor Bio")
    private String instructorBio;

    @Field("Institution")
    private String institution;

    @Field("Institution Rating")
    private String institutionRating;

    @Field("Enrollment Count")
    private String enrollmentCount;

    @Field("Completion Rate")
    private String completionRate;

    @Field("Start Date")
    private String startDate;

    @Field("End Date")
    private String endDate;

    @Field("Weekly Study Hours")
    private String weeklyStudyHours;

    @Field("Number of Lectures")
    private String numberOfLectures;

    @Field("Number of Assignments")
    private String numberOfAssignments;

    @Field("Number of Quizzes")
    private String numberOfQuizzes;

    @Field("Skills Gained")
    private String skillsGained;

    @Field("Career Outcomes")
    private String careerOutcomes;

    @Field("Partner Companies")
    private String partnerCompanies;

    @Field("Accreditation")
    private String accreditation;

    // Getters and setters for all fields


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public String getCertifications() {
        return certifications;
    }

    public void setCertifications(String certifications) {
        this.certifications = certifications;
    }

    public String getPricingType() {
        return pricingType;
    }

    public void setPricingType(String pricingType) {
        this.pricingType = pricingType;
    }

    public String getPriceDetails() {
        return priceDetails;
    }

    public void setPriceDetails(String priceDetails) {
        this.priceDetails = priceDetails;
    }

    public String getCourseFormat() {
        return courseFormat;
    }

    public void setCourseFormat(String courseFormat) {
        this.courseFormat = courseFormat;
    }

    public String getSpecialFeatures() {
        return specialFeatures;
    }

    public void setSpecialFeatures(String specialFeatures) {
        this.specialFeatures = specialFeatures;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(String reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getReviewSummary() {
        return reviewSummary;
    }

    public void setReviewSummary(String reviewSummary) {
        this.reviewSummary = reviewSummary;
    }

    public String getSourceWebsite() {
        return sourceWebsite;
    }

    public void setSourceWebsite(String sourceWebsite) {
        this.sourceWebsite = sourceWebsite;
    }

    public String getCourseURL() {
        return courseURL;
    }

    public void setCourseURL(String courseURL) {
        this.courseURL = courseURL;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSubtitlesAvailable() {
        return subtitlesAvailable;
    }

    public void setSubtitlesAvailable(String subtitlesAvailable) {
        this.subtitlesAvailable = subtitlesAvailable;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getInstructorBio() {
        return instructorBio;
    }

    public void setInstructorBio(String instructorBio) {
        this.instructorBio = instructorBio;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getInstitutionRating() {
        return institutionRating;
    }

    public void setInstitutionRating(String institutionRating) {
        this.institutionRating = institutionRating;
    }

    public String getEnrollmentCount() {
        return enrollmentCount;
    }

    public void setEnrollmentCount(String enrollmentCount) {
        this.enrollmentCount = enrollmentCount;
    }

    public String getCompletionRate() {
        return completionRate;
    }

    public void setCompletionRate(String completionRate) {
        this.completionRate = completionRate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getWeeklyStudyHours() {
        return weeklyStudyHours;
    }

    public void setWeeklyStudyHours(String weeklyStudyHours) {
        this.weeklyStudyHours = weeklyStudyHours;
    }

    public String getNumberOfLectures() {
        return numberOfLectures;
    }

    public void setNumberOfLectures(String numberOfLectures) {
        this.numberOfLectures = numberOfLectures;
    }

    public String getNumberOfAssignments() {
        return numberOfAssignments;
    }

    public void setNumberOfAssignments(String numberOfAssignments) {
        this.numberOfAssignments = numberOfAssignments;
    }

    public String getNumberOfQuizzes() {
        return numberOfQuizzes;
    }

    public void setNumberOfQuizzes(String numberOfQuizzes) {
        this.numberOfQuizzes = numberOfQuizzes;
    }

    public String getSkillsGained() {
        return skillsGained;
    }

    public void setSkillsGained(String skillsGained) {
        this.skillsGained = skillsGained;
    }

    public String getCareerOutcomes() {
        return careerOutcomes;
    }

    public void setCareerOutcomes(String careerOutcomes) {
        this.careerOutcomes = careerOutcomes;
    }

    public String getPartnerCompanies() {
        return partnerCompanies;
    }

    public void setPartnerCompanies(String partnerCompanies) {
        this.partnerCompanies = partnerCompanies;
    }

    public String getAccreditation() {
        return accreditation;
    }

    public void setAccreditation(String accreditation) {
        this.accreditation = accreditation;
    }
}
