package com.jpa.hibernate.repository;

import com.jpa.hibernate.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "courses ")
public interface CourseSpringDataCourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByName(String name);

    List<Course> countByName(String name);

    List<Course> findByNameOrderByIdDesc(String name);

    @Query("Select c From Course c Where name like '%100 Steps'")
    List<Course> courseWith100Steps();

    @Query(value = "Select * From Course c Where name like '%100 Steps'", nativeQuery = true)
    List<Course> courseWith100StepsNative();

    @Query(name = "query_get_all_courses")
    List<Course> getAllCourses();
}
