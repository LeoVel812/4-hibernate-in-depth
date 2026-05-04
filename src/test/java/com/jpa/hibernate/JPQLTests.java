package com.jpa.hibernate;

import com.jpa.hibernate.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = HibernateInDepthApplication.class)
class JPQLTests {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    EntityManager em;

    @Test
    void getAllCoursesJPQLQuery_test() {
        // Understanding JPQL syntax
        // Course -> entity,
        Query selectCFromCourseCQ = em.createQuery("Select c From Course c");
        List selectCFromCourseCResultList = selectCFromCourseCQ.getResultList();
        log.info("Select c From Course c: {}", selectCFromCourseCResultList);
    }

    @Test
    void getAllCoursesJPQLQueryTyped_test() {
        // Understanding JPQL syntax
        // Course -> entity,
        TypedQuery<Course> selectCFromCourseCQuery = em.createQuery("Select c From Course c", Course.class);
        List<Course> selectCFromCourseC = selectCFromCourseCQuery.getResultList();
        log.info("Select c From Course c: {}", selectCFromCourseC);
    }

    @Test
    void getCoursesUsingWhereJPQLQueryTyped_test() {
        // Understanding JPQL syntax
        // Course -> entity,
        String jpqlQuery = """
                Select c From Course c \
                Where name like '%100 Steps'""";
        TypedQuery<Course> usingWhereQuery = em.createQuery(jpqlQuery, Course.class);
        List<Course> usingWhereQueryResultList = usingWhereQuery.getResultList();
        log.info("Select c From Course c Where name like '%100 Steps': {}", usingWhereQueryResultList);
    }
}
