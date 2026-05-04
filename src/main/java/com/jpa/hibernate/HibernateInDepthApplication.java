package com.jpa.hibernate;

import com.jpa.hibernate.entity.Course;
import com.jpa.hibernate.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HibernateInDepthApplication implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final CourseRepository repository;

    public HibernateInDepthApplication(CourseRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(HibernateInDepthApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {//This is the very first executed when the context starts
        log.info("findById(10001): {}", repository.findById(10001L));
        log.info("deleteById(10001)");
        repository.deleteById(10001L);
        log.info("Creating a new course: {}", repository.save(new Course("microservices in 100 steps")));
    }
}
