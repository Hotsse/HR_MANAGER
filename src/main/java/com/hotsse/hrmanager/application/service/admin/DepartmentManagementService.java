package com.hotsse.hrmanager.application.service.admin;

import com.hotsse.hrmanager.domain.department.dto.DepartmentDto;
import com.hotsse.hrmanager.domain.department.dto.DepartmentSaveDto;
import com.hotsse.hrmanager.domain.department.entity.Department;
import com.hotsse.hrmanager.domain.department.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DepartmentManagementService {

    private final DepartmentRepository departmentRepository;

    @Transactional
    public Department getDepartment(String code) {
        return departmentRepository.findById(code)
                .orElseThrow(() -> new IllegalArgumentException("Department not found with code: " + code));
    }

    @Transactional
    public Page<DepartmentDto> getDepartments(String keyword, PageRequest pageRequest) {
        return departmentRepository.findAllByNameContaining(keyword, pageRequest).map(DepartmentDto::convert);
    }

    @Transactional
    public String createDepartment(DepartmentSaveDto dto) {
        Department result = departmentRepository.save(Department.of(dto));
        return result.getCode();
    }

    @Transactional
    public void deleteDepartment(String code) {
        Department department = departmentRepository.findById(code)
                .orElseThrow(() -> new IllegalArgumentException("Department not found with code: " + code));
        departmentRepository.delete(department);
    }
}
