package com.hotsse.hrmanager.domain.department.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DepartmentSaveDto {

    private String code;
    private String name;
    private String upperCode;
    private LocalDate startDate;
    private LocalDate endDate;
}
