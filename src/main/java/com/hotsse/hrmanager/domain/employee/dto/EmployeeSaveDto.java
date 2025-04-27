package com.hotsse.hrmanager.domain.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EmployeeSaveDto {

    private Long seq;
    private String accountId;
    private String name;
    private String deptCode;
    private LocalDate startDate;
    private LocalDate endDate;
}
