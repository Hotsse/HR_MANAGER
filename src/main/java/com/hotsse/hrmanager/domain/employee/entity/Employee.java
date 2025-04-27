package com.hotsse.hrmanager.domain.employee.entity;

import com.hotsse.hrmanager.domain.department.entity.Department;
import com.hotsse.hrmanager.domain.employee.dto.EmployeeSaveDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private Long seq;

    @Column(name = "account_id")
    private String accountId;

    @Column(name = "name")
    private String name;

    @Column(name = "dept_code")
    private String deptCode;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_code", referencedColumnName = "code", insertable = false, updatable = false)
    private Department department;

    @Builder
    public Employee(Long seq, String accountId, String name, String deptCode, LocalDate startDate, LocalDate endDate) {
        this.seq = seq;
        this.accountId = accountId;
        this.name = name;
        this.deptCode = deptCode;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Employee of(EmployeeSaveDto dto) {
        return Employee.builder()
                .seq(dto.getSeq())
                .accountId(dto.getAccountId())
                .name(dto.getName())
                .deptCode(dto.getDeptCode())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .build();
    }
}
