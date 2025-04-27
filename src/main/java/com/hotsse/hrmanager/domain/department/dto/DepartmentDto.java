package com.hotsse.hrmanager.domain.department.dto;

import com.hotsse.hrmanager.domain.department.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class DepartmentDto {

    private String code;
    private String name;
    private String upperCode;
    private LocalDate startDate;
    private LocalDate endDate;

    public static DepartmentDto convert(Department department) {
        return DepartmentDto.builder()
                .code(department.getCode())
                .name(department.getName())
                .upperCode(department.getUpperCode())
                .startDate(department.getStartDate())
                .endDate(department.getEndDate())
                .build();
    }
}
