package com.hotsse.hrmanager.domain.department.entity;

import com.hotsse.hrmanager.domain.department.dto.DepartmentSaveDto;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Department {

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "upper_code")
    private String upperCode;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Builder
    public Department(String code, String name, String upperCode, LocalDate startDate, LocalDate endDate) {
        this.code = code;
        this.name = name;
        this.upperCode = upperCode;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Department of(DepartmentSaveDto dto) {
        return Department.builder()
                .code(dto.getCode())
                .name(dto.getName())
                .upperCode(dto.getUpperCode())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .build();
    }
}
