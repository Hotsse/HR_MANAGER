package com.hotsse.hrmanager.domain.employeeWorkLog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotsse.hrmanager.domain.employeeWorkLog.constants.WorkLogStatus;
import com.hotsse.hrmanager.domain.employeeWorkLog.entity.EmployeeWorkLog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class EmployeeWorkLogDto {

    private Long id;
    private String accountId;
    private LocalDate workDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd HH:mm")
    private LocalDateTime clockIn;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd HH:mm")
    private LocalDateTime clockOut;
    private WorkLogStatus workStatus;
    private String reason;

    public String getWorkStatusName() {
        return Optional.ofNullable(workStatus).map(WorkLogStatus::getName).orElse("");
    }

    public static EmployeeWorkLogDto convert(EmployeeWorkLog workLog) {
        return EmployeeWorkLogDto.builder()
                .id(workLog.getId())
                .accountId(workLog.getAccountId())
                .workDate(workLog.getWorkDate())
                .clockIn(workLog.getClockIn())
                .clockOut(workLog.getClockOut())
                .workStatus(workLog.getWorkStatus())
                .reason(workLog.getReason())
                .build();
    }
}
