package com.jpa.hibernate.repository;

import com.jpa.hibernate.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CourseRepository {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

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
        // this is one transaction
        Course course1 = new Course("Web Services in 100 Steps");
        em.persist(course1);
        //JPA automatically updates this record, no need to call merge
        course1.setName("Web Services in 100 Steps - Updated");
    }
}
