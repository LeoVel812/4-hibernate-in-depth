package com.jpa.hibernate;

import com.jpa.hibernate.entity.Student;
import com.jpa.hibernate.repository.StudentRepository;
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
class StudentRepositoryTests {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    StudentRepository repository;

    @Autowired
    EntityManager em;

    //For tests, use records that aren't modified on HibernateInDepthApplication CommandLineRunner run implementation
    @Test
    // On Student Entity, the fetch type is LAZY, so it only retrieves its fields,
    // (no joins for fields with relationships), the session ends here
    // so it needs the following annotation in order to retrieve the passport
    @Transactional
    void retrieveStudentAndPassportDetails() {
        Student student = em.find(Student.class, 20001L);
        log.info("student: {}", student);
        log.info("student.passport: {}", student.getPassport());
    }
}
