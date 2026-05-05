package com.jpa.hibernate.repository;

import com.jpa.hibernate.entity.Passport;
import com.jpa.hibernate.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class StudentRepository {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    //The persistence context keeps track of all the different entities
    // which are changed during a specific transaction,
    // it also keeps track of al the changes that needs to be stored back to the DB.
    @PersistenceContext
    private final EntityManager em;

    public StudentRepository(EntityManager em) {
        this.em = em;
    }

    public Student findById(Long id) {
        return em.find(Student.class, id);
    }

    public Student save(Student student) {
        if (student.getId() == null) em.persist(student);// update:
        else em.merge(student);// create a new record:
        return student;
    }

    public void deleteById(Long id) {
        Student student = findById(id);
        if (student != null) em.remove(student);
    }

    public void saveStudentWithPassport() {
        log.info("saveStudentWithPassport");
        Passport passport = new Passport("Z123456");
        em.persist(passport);

        Student student = new Student("Mike");
        student.setPassport(passport);
        em.persist(student);
    }
}
