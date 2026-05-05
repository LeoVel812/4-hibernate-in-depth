package com.jpa.hibernate;

import com.jpa.hibernate.entity.Course;
import com.jpa.hibernate.entity.Review;
import com.jpa.hibernate.entity.Student;
import com.jpa.hibernate.repository.CourseRepository;
import com.jpa.hibernate.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

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

        // OneToOne relationship:
//        studentRepository.saveStudentWithPassport();

        // OneToMany relationship:
//        courseRepository.addReviewsForCourse();
//        courseRepository.addReviewsForCourse(10006L,
//                List.of(new Review("5", "Wonderful hands-on stuff"),
//                        new Review("3", "Mid stuff"),
//                        new Review("4", "Gloves off!")));

        // ManyToMany relationship:
        studentRepository.insertHardcodedStudentAndCourse();
        studentRepository.insertStudentAndCourse(new Student("Patricio"),
                new Course("LLegar a la tonta Texas"));
    }
}
