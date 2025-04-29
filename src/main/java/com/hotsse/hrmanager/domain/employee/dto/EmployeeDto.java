package com.hotsse.hrmanager.domain.employee.dto;

import com.hotsse.hrmanager.domain.employee.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class EmployeeDto {

    private Long seq;
    private String accountId;
    private String name;
    private String deptCode;
    private String deptName;
    private String positionCode;
    private String positionName;
    private LocalDate startDate;
    private LocalDate endDate;

    public static EmployeeDto convert(Employee employee) {
        return EmployeeDto.builder()
                .seq(employee.getSeq())
                .accountId(employee.getAccountId())
                .name(employee.getName())
                .deptCode(employee.getDeptCode())
                .deptName(employee.getDepartment() != null ? employee.getDepartment().getName() : null)
                .positionCode(employee.getPositionCode())
                .positionName(employee.getPosition() != null ? employee.getPosition().getName() : null)
                .startDate(employee.getStartDate())
                .endDate(employee.getEndDate())
                .build();
    }
}
