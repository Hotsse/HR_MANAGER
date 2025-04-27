package com.hotsse.hrmanager.domain.employee.repository;

import com.hotsse.hrmanager.domain.employee.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Page<Employee> findAllByAccountIdContaining(String keyword, Pageable pageable);
}
