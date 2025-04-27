package com.hotsse.hrmanager.application.controller.admin;

import com.hotsse.hrmanager.application.service.admin.EmployeeManagementService;
import com.hotsse.hrmanager.domain.employee.dto.EmployeeDto;
import com.hotsse.hrmanager.domain.employee.dto.EmployeeSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/employee")
@RequiredArgsConstructor
public class EmployeeManagementController {

    private final EmployeeManagementService employeeManagementService;

    @GetMapping
    public ResponseEntity<Page<EmployeeDto>> get(@RequestParam(defaultValue = "") String keyword,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<EmployeeDto> employees = employeeManagementService.getEmployees(keyword, pageRequest);
        return ResponseEntity.ok(employees);
    }

    @PostMapping
    public ResponseEntity<Long> createEmployee(@RequestBody EmployeeSaveDto dto) {
        Long seq = employeeManagementService.createEmployee(dto);
        return ResponseEntity.ok(seq);
    }

    @DeleteMapping("/{employeeSeq}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("employeeSeq") Long employeeSeq) {
        employeeManagementService.deleteEmployee(employeeSeq);
        return ResponseEntity.noContent().build();
    }
}
