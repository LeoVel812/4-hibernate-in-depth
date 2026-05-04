package com.jpa.hibernate;

import com.jpa.hibernate.entity.Course;
import com.jpa.hibernate.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = HibernateInDepthApplication.class)
//@DirtiesContext
class CourseRepositoryTests {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    CourseRepository repository;

    //For tests, use records that aren't modified on HibernateInDepthApplication CommandLineRunner run implementation
    @Test
    void findById_test() {
        log.info("Testing findById");
        Course course = repository.findById(10006L);
        assertEquals("TestContainers in 17 Steps", course.getName());
    }

    @Test
    @DirtiesContext // This resets the modified records in the database
    void deleteById_test() {
        log.info("Testing deleteById");
        repository.deleteById(10005L);
        assertNull(repository.findById(10005L));
    }

    @Test
    @DirtiesContext // This resets the modified records in the database
    void save_test() {
        Course course = repository.findById(10004L);
        assertEquals("SpringBoot in 15 Steps", course.getName());

        course.setName("SpringBoot in 15 Steps - Updated");
        repository.save(course);

        Course course4 = repository.findById(10004L);
        assertEquals("SpringBoot in 15 Steps - Updated", course4.getName());
    }

}
