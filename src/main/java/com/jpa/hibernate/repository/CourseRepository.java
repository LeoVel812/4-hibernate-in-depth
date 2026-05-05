package com.jpa.hibernate.repository;

import com.jpa.hibernate.entity.Course;
import com.jpa.hibernate.entity.Review;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class CourseRepository {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    //The persistence context keeps track of all the different entities
    // which are changed during a specific transaction,
    // it also keeps track of al the changes that needs to be stored back to the DB.
    @PersistenceContext
    private final EntityManager em;

    public CourseRepository(EntityManager em) {
        this.em = em;
    }

    public Course findById(Long id) {
        return em.find(Course.class, id);
    }

    public Course save(Course course) {
        if (course.getId() == null) em.persist(course);// update:
        else em.merge(course);// create a new record:
        return course;
    }

    public void deleteById(Long id) {
        Course course = findById(id);
        if (course != null) em.remove(course);
    }

    public void playWithEntityManager() {
        log.info("playWithEntityManager");

        Course course1 = new Course("Web Services in 100 Steps");
        em.persist(course1);
        Course course2 = new Course("Angular Js in 100 Steps");
        em.persist(course2);
        em.flush(); // sends this to the DB

//        em.detach(course2); // this disengages course2 from the entity manager, course2 won't be updated
//        em.clear(); // this disengages all from the entity manager, nothing is updated in the database

        course1.setName("Web Services in 100 Steps - Updated");
        course2.setName("Angular Js in 100 Steps - Updated");
        // gets the last value of course1(select query) and keeps it,
        // the new value of course 1 won't be sent to the DB,
        em.refresh(course1);
        em.flush();// sends this to the DB
    }

    public void addReviewsForCourse() {
        // get course 10002
        Course course = findById(10002L);
        log.info("course.reviews: {}", course.getReviews());

        // add 2 reviews to it
        Review review1 = new Review("5", "Great Hands-on stuff");
        Review review2 = new Review("4", "Hats off!");

        // setting the relationship:
        course.addReview(review1);
        review1.setCourse(course);

        course.addReview(review2);
        review2.setCourse(course);

        // save it to the DB
        em.persist(review1);
        em.persist(review2);
    }

    public void addReviewsForCourse(Long courseId, List<Review> reviews) {
        // get course 10002
        Course course = findById(courseId);
        log.info("course.reviews: {}", course.getReviews());
        reviews.forEach(review -> {
            // setting the relationship:
            course.addReview(review);
            review.setCourse(course);

            // save it to the DB
            em.persist(review);
        });

    }
}
