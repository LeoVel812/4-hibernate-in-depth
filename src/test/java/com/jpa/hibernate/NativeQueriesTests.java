package com.jpa.hibernate;

import com.jpa.hibernate.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = HibernateInDepthApplication.class)
class NativeQueriesTests {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    EntityManager em;

    @Test
    void getAllCoursesSQLQuery_test() {
        String sqlQuery = "SELECT * FROM COURSE";
        Query selectAllCoursesQuery = em.createNativeQuery(sqlQuery, Course.class);
        List selectAllResultList = selectAllCoursesQuery.getResultList();
        log.info("select * from course : {}", selectAllResultList);
    }

    @Test
    void getCourseByIdSQLQueryUsingPlaceholder_test() {
        String sqlQuery = """
                SELECT * FROM COURSE \
                WHERE id = ?""";
        Query getCourseByIdQuery = em.createNativeQuery(sqlQuery, Course.class);
        getCourseByIdQuery.setParameter(1, 10001L);
        List getCourseByIdQueryResultList = getCourseByIdQuery.getResultList();
        log.info("select * from course where id = ? : {}", getCourseByIdQueryResultList);
    }

    @Test
    void getCourseByIdSQLQueryUsingNamedParameter_test() {
        String sqlQuery = """
                SELECT * FROM COURSE \
                WHERE id = :id""";
        Query getCourseByIdQuery = em.createNativeQuery(sqlQuery, Course.class);
        getCourseByIdQuery.setParameter("id", 10006L);
        List getCourseByIdQueryResultList = getCourseByIdQuery.getResultList();
        log.info("select * from course where id = :id : {}", getCourseByIdQueryResultList);
    }

    //Batch operations are not implemented on JPA-Hibernate, so use native queries
    @Test
    @Transactional
    void batchUpdateSQLQuery_test() {
        String sqlQuery = """
                UPDATE COURSE \
                SET last_updated_date = current_timestamp()""";
        Query batchUpdateQuery = em.createNativeQuery(sqlQuery, Course.class);
        int rowsUpdated = batchUpdateQuery.executeUpdate();
        log.info("batch update, rowsUpdated : {}", rowsUpdated);
    }
}
