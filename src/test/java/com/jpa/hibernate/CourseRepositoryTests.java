package com.jpa.hibernate;

import com.jpa.hibernate.entity.Course;
import com.jpa.hibernate.entity.Review;
import com.jpa.hibernate.repository.CourseRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = HibernateInDepthApplication.class)
class CourseRepositoryTests {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepository repository;

    @Autowired
    EntityManager em; //instead of this, create the reviewRepository

    //For tests, use records that aren't modified on HibernateInDepthApplication CommandLineRunner run implementation
    @Test
    void findById_test() {
        log.info("Testing findById");
        Course course = repository.findById(10006L);
        assertEquals("TestContainers in 17 Steps", course.getName());
    }

    @Test
    @DirtiesContext
        // This resets the modified records in the database
    void deleteById_test() {
        log.info("Testing deleteById");
        repository.deleteById(10005L);
        assertNull(repository.findById(10005L));
    }

    @Test
    @DirtiesContext
        // This resets the modified records in the database
    void save_test() {
        Course course = repository.findById(10004L);
        assertEquals("SpringBoot in 15 Steps", course.getName());

        course.setName("SpringBoot in 15 Steps - Updated");
        repository.save(course);

        Course course4 = repository.findById(10004L);
        assertEquals("SpringBoot in 15 Steps - Updated", course4.getName());
    }

    @Test
    @DirtiesContext
        // This resets the modified records in the database
    void playWithEntityManager_test() {
        // Understanding @Transactional
        repository.playWithEntityManager();
    }

    //Playing with OneToMany relationship:
    @Test
    @Transactional
    // By default, OneToMany is Lazy fetch type
    // execute select query(Course) and then join query(Reviews)
    void retrieveReviewsFromCourse() {
        Course course = repository.findById(10002L);
        log.info("course.reviews: {}", course.getReviews());
    }

    //Playing with ManyToOne relationship:
    @Test
//    @Transactional
    // By default, ManyToOne is Eager fetch type
    void retrieveCourseFromReview() {
        Review review = em.find(Review.class, 50001L);
        log.info("review.course: {}", review.getCourse());

        //checking if Eager Fetch type relates to @Transactional :yes
        Review review2 = em.find(Review.class, 50003L);
        log.info("review.course: {}", review2.getCourse());
    }

    @Test
    // In order to be cached on First Level, use @Transactional
    // First Level Cache only is within the boundary of a SINGLE Transaction
    @Transactional
    void findById_firstLevelCacheDemo() {
        // It doesn't get cached when @Transactional is not used
        log.info("Testing findById, first level demo");
        Course course = repository.findById(10006L);
        log.info("First time retrieving course: {}", course);
        Course course1 = repository.findById(10006L);
        log.info("Second time retrieving course: {}", course1);
    }
}
