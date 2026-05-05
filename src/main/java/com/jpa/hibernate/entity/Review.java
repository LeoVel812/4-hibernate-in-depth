package com.jpa.hibernate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {
    @Id
    @GeneratedValue
    private Long id;

    // By default, this is ordinal, so in the sql inserts must be numbers
    // it's better to be string type, it's more flexible and modifiable
    @Enumerated(EnumType.STRING)
    private ReviewRating rating;

    private String description;

    // This is the owning side of the relationship,
    // this table will have the course_id column (fk)
    @ManyToOne
    // By default, ManyToOne is Eager fetch type
    private Course course;

    protected Review() {
    }

    public Review(ReviewRating rating, String description) {
        this.rating = rating;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReviewRating getRating() {
        return rating;
    }

    public void setRating(ReviewRating rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return String.format("Student[id=%d, rating=%s, desc=%s]", id, rating, description);
    }
}

