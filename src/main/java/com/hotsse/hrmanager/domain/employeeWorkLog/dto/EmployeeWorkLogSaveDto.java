package com.hotsse.hrmanager.domain.employeeWorkLog.dto;

import com.hotsse.hrmanager.domain.employeeWorkLog.constants.WorkLogStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EmployeeWorkLogSaveDto {

    private Long id;
    private String accountId;
    private LocalDate workDate;
    private LocalDateTime clockIn;
    private LocalDateTime clockOut;
    private WorkLogStatus workStatus;
    private String reason;
}
