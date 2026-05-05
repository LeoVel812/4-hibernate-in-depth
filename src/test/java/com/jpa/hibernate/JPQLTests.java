package com.jpa.hibernate;

import com.jpa.hibernate.entity.Course;
import com.jpa.hibernate.entity.Student;
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
        Query selectCFromCourseCQ = em.createNamedQuery("query_get_all_courses");
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
//        String jpqlQuery = """
//                Select c From Course c \
//                Where name like '%100 Steps'""";
//        TypedQuery<Course> usingWhereQuery = em.createQuery(jpqlQuery, Course.class);
        TypedQuery<Course> usingWhereQuery = em.createNamedQuery("query_get_100_steps_courses", Course.class);
        List<Course> usingWhereQueryResultList = usingWhereQuery.getResultList();
        log.info("Select c From Course c Where name like '%100 Steps': {}", usingWhereQueryResultList);
    }

    // Playing with complex queries, taking advantage of the established relationships
    @Test
    void jpqlCoursesWithoutStudents() {
        String jpqlQuery = """
                Select c From Course c \
                Where c.students is empty""";
        TypedQuery<Course> usingEmptyQuery = em.createQuery(jpqlQuery, Course.class);
        List<Course> usingEmptyQueryResultList = usingEmptyQuery.getResultList();
        log.info("courses with no students: {}", usingEmptyQueryResultList);
    }

    @Test
    void jpqlCoursesMoreThan2Students() {
        String jpqlQuery = """
                Select c From Course c \
                Where size(c.students) >= 2""";
        TypedQuery<Course> usingEmptyQuery = em.createQuery(jpqlQuery, Course.class);
        List<Course> usingEmptyQueryResultList = usingEmptyQuery.getResultList();
        log.info("courses with more than 2 students: {}", usingEmptyQueryResultList);
    }

    @Test
    void jpqlCoursesOrderedByStudentsAsc() {
        String jpqlQuery = """
                Select c From Course c \
                Order By size(c.students)""";
        TypedQuery<Course> usingEmptyQuery = em.createQuery(jpqlQuery, Course.class);
        List<Course> usingEmptyQueryResultList = usingEmptyQuery.getResultList();
        log.info("courses order by size of students asc: {}", usingEmptyQueryResultList);
    }

    @Test
    void jpqlCoursesOrderedByStudentsDesc() {
        String jpqlQuery = """
                Select c From Course c \
                Order By size(c.students) Desc""";
        TypedQuery<Course> usingEmptyQuery = em.createQuery(jpqlQuery, Course.class);
        List<Course> usingEmptyQueryResultList = usingEmptyQuery.getResultList();
        log.info("courses order by size of students desc: {}", usingEmptyQueryResultList);
    }

    @Test
    void jpqlStudentsWithPassportLikePattern() {
        String jpqlQuery = """
                Select s From Student s \
                Where s.passport.number like '%134%'""";
        TypedQuery<Student> usingEmptyQuery = em.createQuery(jpqlQuery, Student.class);
        List<Student> usingEmptyQueryResultList = usingEmptyQuery.getResultList();
        log.info("student.passports with pattern 134: {}", usingEmptyQueryResultList);
    }

    // playing with joins
    //JOIN: Select c, s From Course c Join c.students s, it retrieves only the matching ones
    //LEFT JOIN: Select c, s From Course c Join c.students s, it retrieves also the null ones
    //JOIN: Select c, s From Course c, Student s, it makes like a cross product
    @Test
    void join() {
        String jpqlQuery = """
                Select c, s \
                From Course c \
                Join c.students s""";
        Query joinQuery = em.createQuery(jpqlQuery);
        List<Object[]> joinQueryResultList = joinQuery.getResultList();
        log.info("joinQueryResultList.size(): {}", joinQueryResultList.size());
        joinQueryResultList.forEach(array ->
                log.info("course: {}, student: {}", array[0], array[1]));
    }

    @Test
    void leftJoin() {
        String jpqlQuery = """
                Select c, s \
                From Course c \
                Left Join c.students s""";
        Query joinQuery = em.createQuery(jpqlQuery);
        List<Object[]> joinQueryResultList = joinQuery.getResultList();
        log.info("joinQueryResultList.size(): {}", joinQueryResultList.size());
        joinQueryResultList.forEach(array ->
                log.info("course: {}, student: {}", array[0], array[1]));
    }

    @Test
    void crossJoin() {
        String jpqlQuery = """
                Select c, s \
                From Course c, Student s""";
        Query joinQuery = em.createQuery(jpqlQuery);
        List<Object[]> joinQueryResultList = joinQuery.getResultList();
        log.info("joinQueryResultList.size(): {}", joinQueryResultList.size());
        joinQueryResultList.forEach(array ->
                log.info("course: {}, student: {}", array[0], array[1]));
    }
}
