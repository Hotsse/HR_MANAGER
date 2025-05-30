package com.hotsse.hrmanager.domain.department.repository;

import com.hotsse.hrmanager.domain.department.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {

    Page<Department> findAllByNameContaining(String keyword, Pageable pageable);
}
