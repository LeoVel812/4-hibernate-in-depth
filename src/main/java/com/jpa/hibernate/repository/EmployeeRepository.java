package com.jpa.hibernate.repository;

import com.jpa.hibernate.entity.Employee;
import com.jpa.hibernate.entity.FullTimeEmployee;
import com.jpa.hibernate.entity.PartTimeEmployee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class EmployeeRepository {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private final EntityManager em;

    public EmployeeRepository(EntityManager em) {
        this.em = em;
    }

    //insert an employee
    public void insert(Employee employee) {
        log.info("Insert a new employee");
        em.persist(employee);
    }

    //retrieve all employees
    public List<Employee> retrieveAllEmployee() {
        log.info("Retrieve all employees");
        return em.createQuery("Select e From Employee e", Employee.class)
                .getResultList();
    }

    //retrieve all partTimeEmployees
    public List<PartTimeEmployee> retrievePartTimeEmployees() {
        log.info("Retrieve all partTimeEmployees");
        return em.createQuery("Select e From PartTimeEmployee e", PartTimeEmployee.class)
                .getResultList();
    }

    //retrieve all fullTimeEmployees
    public List<FullTimeEmployee> retrieveFullTimeEmployees() {
        log.info("Retrieve all fullTimeEmployees");
        return em.createQuery("Select e From FullTimeEmployee e", FullTimeEmployee.class)
                .getResultList();
    }

}
