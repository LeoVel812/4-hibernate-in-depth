package com.jpa.hibernate;

import com.jpa.hibernate.entity.Course;
import com.jpa.hibernate.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = HibernateInDepthApplication.class)
class CriteriaQueryTests {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    EntityManager em;

    @Test
    void getAllCoursesCriteriaQuery() {
        //"Select c From Course c"
        // 1. Use Criteria Builder to create a criteria Query returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. Define roots for tables which are invilved in the query
        Root<Course> courseRoot = cq.from(Course.class);
        // 3. Define Predicate etc, using Criteria Builder
        // 4. Add Predicates etc to the Criteria Query
        // 5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> selectCFromCourseCQ = em.createQuery(cq.select(courseRoot));
        List<Course> selectCFromCourseCResultList = selectCFromCourseCQ.getResultList();
        log.info("get all Courses using CriteriaQuery: {}", selectCFromCourseCResultList);
    }

    @Test
    void getAllCoursesLike100StepsCriteriaQuery() {
        //"Select c From Course c Where name like '%100 Steps'"
        // 1. Use Criteria Builder to create a criteria Query returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. Define roots for tables which are invilved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        // 3. Define Predicate etc, using Criteria Builder
        Predicate like100Steps = cb.like(courseRoot.get("name"), "%100 Steps");

        // 4. Add Predicates etc to the Criteria Query
        cq.where(like100Steps);

        // 5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> selectCFromCourseCQ = em.createQuery(cq.select(courseRoot));
        List<Course> selectCFromCourseCResultList = selectCFromCourseCQ.getResultList();
        log.info("get all Courses like 100 Steps using CriteriaQuery: {}", selectCFromCourseCResultList);
    }

    @Test
    void getAllCoursesWithoutStudentsCriteriaQuery() {
        //"Select c From Course c Where c.students is Empty"
        // 1. Use Criteria Builder to create a criteria Query returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. Define roots for tables which are invilved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        // 3. Define Predicate etc, using Criteria Builder
        Predicate isEmpty = cb.isEmpty(courseRoot.get("students"));

        // 4. Add Predicates etc to the Criteria Query
        cq.where(isEmpty);

        // 5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> selectCFromCourseCQ = em.createQuery(cq.select(courseRoot));
        List<Course> selectCFromCourseCResultList = selectCFromCourseCQ.getResultList();
        log.info("get all Courses without students using CriteriaQuery: {}", selectCFromCourseCResultList);
    }

    @Test
    void joinCriteriaQuery() {
        //"Select c From Course c Join c.students s"
        // 1. Use Criteria Builder to create a criteria Query returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. Define roots for tables which are invilved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        // 3. Define Predicate etc, using Criteria Builder
        Join<Object, Object> join = courseRoot.join("students");

        // 4. Add Predicates etc to the Criteria Query

        // 5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> selectCFromCourseCQ = em.createQuery(cq.select(courseRoot));
        List<Course> selectCFromCourseCResultList = selectCFromCourseCQ.getResultList();
        log.info("get all Courses without students using CriteriaQuery: {}", selectCFromCourseCResultList);
    }

    @Test
    void leftJoinCriteriaQuery() {
        //"Select c From Course c Left Join c.students s"
        // 1. Use Criteria Builder to create a criteria Query returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. Define roots for tables which are invilved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        // 3. Define Predicate etc, using Criteria Builder
        Join<Object, Object> join = courseRoot.join("students", JoinType.LEFT);

        // 4. Add Predicates etc to the Criteria Query

        // 5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> selectCFromCourseCQ = em.createQuery(cq.select(courseRoot));
        List<Course> selectCFromCourseCResultList = selectCFromCourseCQ.getResultList();
        log.info("get all Courses without students using CriteriaQuery: {}", selectCFromCourseCResultList);
    }

}
