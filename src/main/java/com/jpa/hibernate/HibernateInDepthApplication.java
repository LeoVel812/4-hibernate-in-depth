package com.jpa.hibernate;

import com.jpa.hibernate.entity.Course;
import com.jpa.hibernate.repository.CourseRepository;
import com.jpa.hibernate.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HibernateInDepthApplication implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public HibernateInDepthApplication(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(HibernateInDepthApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {//This is the very first executed when the context starts
//        log.info("findById(10001): {}", courseRepository.findById(10001L));
//        log.info("deleteById(10001)");
//        courseRepository.deleteById(10001L);
//        log.info("Creating a new course: {}", courseRepository.save(new Course("microservices in 100 steps")));
//         // // Understanding @Transactional of EntityManager
//        courseRepository.playWithEntityManager();

        studentRepository.saveStudentWithPassport();

    }
}
