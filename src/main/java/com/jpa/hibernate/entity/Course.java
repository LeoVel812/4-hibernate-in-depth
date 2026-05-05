package com.jpa.hibernate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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

    // By default, OneToMany is Lazy fetch type
    @OneToMany(mappedBy = "course")
    private List<Review> reviews = new ArrayList<>();

    // By default, ManyToMany is Lazy fetch type
    // With no mappedBy, this makes two tables of joined:
    // plural names: student_courses(students_id,courses_id)
    // making the Student Entity the owning side of the relationship
    // In the owning-side, add @JoinTable and tune the relationship-table
    // name table and col names
    // joinColumn - STUDENT_ID
    // inverseJoinColumn - COURSE_ID
    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();

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

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
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
