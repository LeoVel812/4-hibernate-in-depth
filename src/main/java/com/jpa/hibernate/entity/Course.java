package com.jpa.hibernate.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries(value = {
        @NamedQuery(name = "query_get_all_courses", query = "Select c From Course c"),
        @NamedQuery(name = "query_get_100_steps_courses", query = "Select c From Course c Where name like '%100 Steps'")})
public class Course {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "course")
    private List<Review> reviews = new ArrayList<>();

    @UpdateTimestamp //automatically creates this column when the record is updated
    private LocalDateTime lastUpdatedDate;

    @CreationTimestamp //automatically creates this column when the record is created
    private LocalDateTime createdDate;

    protected Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public void removeReview(Review review) {
        this.reviews.remove(review);
    }

    public LocalDateTime getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    @Override
    public String toString() {
        return String.format("Course[name=%s, id=%d]", name, id);
    }

}
