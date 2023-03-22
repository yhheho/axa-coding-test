package com.monstarlab.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.monstarlab.movie.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
