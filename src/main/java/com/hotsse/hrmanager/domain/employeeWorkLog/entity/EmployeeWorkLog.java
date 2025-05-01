package com.hotsse.hrmanager.domain.employeeWorkLog.entity;

import com.hotsse.hrmanager.domain.employeeWorkLog.constants.WorkLogStatus;
import com.hotsse.hrmanager.domain.employeeWorkLog.dto.EmployeeWorkLogSaveDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmployeeWorkLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "account_id")
    private String accountId;

    @Column(name = "work_date")
    private LocalDate workDate;

    @Column(name = "clock_in")
    private LocalDateTime clockIn;

    @Column(name = "clock_out")
    private LocalDateTime clockOut;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private WorkLogStatus workStatus;

    @Column(name = "reason")
    private String reason;

    @Builder
    public EmployeeWorkLog(Long id, String accountId, LocalDate workDate, LocalDateTime clockIn, LocalDateTime clockOut, WorkLogStatus workStatus, String reason) {
        this.id = id;
        this.accountId = accountId;
        this.workDate = workDate;
        this.clockIn = clockIn;
        this.clockOut = clockOut;
        this.workStatus = workStatus;
        this.reason = reason;
    }

    public void clockOut(LocalDateTime dateTime) {
        this.clockOut = dateTime;
    }

    public static EmployeeWorkLog of(EmployeeWorkLogSaveDto dto) {
        return EmployeeWorkLog.builder()
                .id(dto.getId())
                .accountId(dto.getAccountId())
                .workDate(dto.getWorkDate())
                .clockIn(dto.getClockIn())
                .clockOut(dto.getClockOut())
                .workStatus(dto.getWorkStatus())
                .reason(dto.getReason())
                .build();
    }
}
