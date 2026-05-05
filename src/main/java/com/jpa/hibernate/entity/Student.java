package com.jpa.hibernate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Embedded
    private Address address;

    //When querying Student it doesn't retrieve passport with LAZY,
    // session immediately ends and only selects student and not join for passport
    // to retrieve it, use @Transactional on methods, when querying student.passport
    @OneToOne(fetch = FetchType.LAZY)
    private Passport passport;

    // By default, ManyToMany is Lazy fetch type
    // With no mappedBy, this makes two tables of joined:
    // plural names: student_courses(students_id,courses_id)
    // making the Student Entity the owning side of the relationship
    // In the owning-side, add @JoinTable and tune the relationship-table
    // name table and col names
    // joinColumn - STUDENT_ID
    // inverseJoinColumn - COURSE_ID
    @ManyToMany
    @JoinTable(name = "STUDENT_COURSE",
            joinColumns = @JoinColumn(name = "STUDENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "COURSE_ID"))
    private List<Course> courses = new ArrayList<>();

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    protected Student() {
    }

    public Student(String name) {
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public void removeCourse(Course course) {
        this.courses.remove(course);
    }

    @Override
    public String toString() {
        return String.format("Student[id=%d, name=%s]", id, name);
    }

}
