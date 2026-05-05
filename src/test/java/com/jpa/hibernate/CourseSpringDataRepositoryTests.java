package com.jpa.hibernate;

import com.jpa.hibernate.entity.Course;
import com.jpa.hibernate.entity.Review;
import com.jpa.hibernate.repository.CourseRepository;
import com.jpa.hibernate.repository.CourseSpringDataCourseRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = HibernateInDepthApplication.class)
class CourseSpringDataRepositoryTests {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    CourseSpringDataCourseRepository repository;

    @Test
    void findById_CoursePresent() {
        Optional<Course> course = repository.findById(10001L);
        assertTrue(course.isPresent());
    }

    @Test
    void findById_CourseNotPresent() {
        Optional<Course> course = repository.findById(20001L);
        assertFalse(course.isPresent());
    }

    @Test
    void playingAroundWithSpringDataRepository() {
        Course course = new Course("Microservices in 20 steps");
        repository.save(course);

        course.setName("Microservices in 10 steps - Updated");
        repository.save(course);

        log.info("All Courses: {}", repository.findAll());
        log.info("Courses count: {}", repository.count());
    }

    @Test
    void sort() {
        Sort sort = Sort.by(Sort.Direction.DESC, "name")
                .and(Sort.by(Sort.Direction.ASC, "id"));

        log.info("Sorted Courses: {}", repository.findAll(sort));
        log.info("Courses count: {}", repository.count());
    }

    @Test
    void pagination() {
        Sort sort = Sort.by(Sort.Direction.DESC, "name")
                .and(Sort.by(Sort.Direction.ASC, "id"));
        PageRequest firstPageRequest = PageRequest.of(0, 5, sort);
        Page<Course> firstPage = repository.findAll(firstPageRequest);
        log.info("page 1 Courses: {}", firstPage.getContent());

        Pageable secondPageRequest = firstPage.nextPageable();
        Page<Course> secondPage = repository.findAll(secondPageRequest);
        log.info("page 2 Courses: {}", secondPage.getContent());

        Pageable thirdPageRequest = secondPage.nextPageable();
        Page<Course> thirdPage = repository.findAll(thirdPageRequest);
        log.info("page 3 Courses: {}", thirdPage.getContent());

    }

    @Test
    void findByName() {
        log.info("FindByName: {}", repository.findByName("Dummy 7"));
    }

}
