package com.hotsse.hrmanager.application.service.admin;

import com.hotsse.hrmanager.domain.employee.dto.EmployeeDto;
import com.hotsse.hrmanager.domain.employee.dto.EmployeeSaveDto;
import com.hotsse.hrmanager.domain.employee.entity.Employee;
import com.hotsse.hrmanager.domain.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmployeeManagementService {

    private final EmployeeRepository employeeRepository;

    @Transactional
    public Page<EmployeeDto> getEmployees(String keyword, PageRequest pageRequest) {
        return employeeRepository.findAllByAccountIdContaining(keyword, pageRequest).map(EmployeeDto::convert);
    }

    @Transactional
    public Long createEmployee(EmployeeSaveDto dto) {
        Employee result = employeeRepository.save(Employee.of(dto));
        return result.getSeq();
    }

    @Transactional
    public void deleteEmployee(Long seq) {
        Employee employee = employeeRepository.findById(seq)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        employeeRepository.delete(employee);
    }
}
