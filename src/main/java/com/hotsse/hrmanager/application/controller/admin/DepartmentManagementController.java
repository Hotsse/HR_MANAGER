package com.hotsse.hrmanager.application.controller.admin;

import com.hotsse.hrmanager.application.service.admin.DepartmentManagementService;
import com.hotsse.hrmanager.domain.department.dto.DepartmentDto;
import com.hotsse.hrmanager.domain.department.dto.DepartmentSaveDto;
import com.hotsse.hrmanager.domain.department.entity.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/department")
@RequiredArgsConstructor
public class DepartmentManagementController {

    private final DepartmentManagementService departmentManagementService;

    @GetMapping("/{deptCode}")
    public ResponseEntity<Department> getOne(@PathVariable("deptCode") String deptCode) {
        Department department = departmentManagementService.getDepartment(deptCode);
        return ResponseEntity.ok(department);
    }

    @GetMapping
    public ResponseEntity<Page<DepartmentDto>> get(@RequestParam(defaultValue = "") String keyword,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<DepartmentDto> departments = departmentManagementService.getDepartments(keyword, pageRequest);
        return ResponseEntity.ok(departments);
    }

    @PostMapping
    public ResponseEntity<String> createDepartment(@RequestBody DepartmentSaveDto dto) {
        String code = departmentManagementService.createDepartment(dto);
        return ResponseEntity.ok(code);
    }

    @DeleteMapping("/{deptCode}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable("deptCode") String deptCode) {
        departmentManagementService.deleteDepartment(deptCode);
        return ResponseEntity.noContent().build();
    }
}
