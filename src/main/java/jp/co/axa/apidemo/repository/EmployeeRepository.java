package jp.co.axa.apidemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import jp.co.axa.apidemo.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
