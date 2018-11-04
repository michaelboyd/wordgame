package com.clearcorrect.wordgame.employee;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@Repository
//@Transactional
@RepositoryRestResource(collectionResourceRel = "employee", path = "employee")
public interface EmployeeRepository extends CrudRepository <Employee, Long> {

    public Employee findByName(String name);

    public List<Employee> findAll();

}
