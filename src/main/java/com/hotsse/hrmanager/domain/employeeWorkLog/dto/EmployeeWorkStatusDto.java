package com.hotsse.hrmanager.domain.employeeWorkLog.dto;

import com.hotsse.hrmanager.domain.employeeWorkLog.constants.WorkStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class EmployeeWorkStatusDto {

    private LocalDate workDate;
    private WorkStatus workStatus;
    private Boolean isLateClockIn;
    private List<EmployeeWorkLogDto> workLogs;
}
