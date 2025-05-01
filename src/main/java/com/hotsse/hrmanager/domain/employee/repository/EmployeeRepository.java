package com.hotsse.hrmanager.domain.employee.repository;

import com.hotsse.hrmanager.domain.employee.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Page<Employee> findAllByAccountIdContaining(String keyword, Pageable pageable);

    Optional<Employee> findByAccountId(String accountId);

    default Employee findByAccountIdOrElseThrow(String accountId) {
        return findByAccountId(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with accountId: " + accountId));
    }
}
