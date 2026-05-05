package com.jpa.hibernate;

import com.jpa.hibernate.entity.Course;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Subgraph;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = HibernateInDepthApplication.class)
class PerformanceTuningTests {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em; //instead of this, create the reviewRepository

    @Test
    @Transactional
        // By default, OneToMany is Lazy fetch type
        // execute select query(Course) and then join query(Reviews)
        // this is a n + 1 problem
    void findAll_nPlusOneProblem_test() {
        log.info("Testing findAll with n + 1 problem");
        List<Course> courses = em.createNamedQuery("query_get_all_courses", Course.class)
                .getResultList();
        courses.forEach(course ->
                log.info("course: {}, students: {}", course, course.getStudents()));
    }

    @Test
    @Transactional
        // By default, OneToMany is Lazy fetch type
        // execute select query(Course) and then join query(Reviews)
        // solving this is a n + 1 problem
    void findAll_withEntityGraph_test() {
        log.info("Testing findAll with EntityGraph");
        EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);
//        Subgraph<Object> subGraph = entityGraph.addSubgraph("students");
        entityGraph.addAttributeNodes("students");

        List<Course> courses = em.createNamedQuery("query_get_all_courses", Course.class)
                .setHint("jakarta.persistence.loadgraph", entityGraph)
                .getResultList();
        courses.forEach(course ->
                log.info("course: {}, students: {}", course, course.getStudents()));
    }

    @Test
    @Transactional
        // By default, OneToMany is Lazy fetch type
        // execute select query(Course) and then join query(Reviews)
        // solving this is a n + 1 problem
    void findAll_joinFetch_test() {
        log.info("Testing findAll with n + 1 problem, join fetch");
        List<Course> courses = em.createNamedQuery("query_get_all_courses_join_fetch", Course.class)
                .getResultList();
        courses.forEach(course ->
                log.info("course: {}, students: {}", course, course.getStudents()));
    }
}
